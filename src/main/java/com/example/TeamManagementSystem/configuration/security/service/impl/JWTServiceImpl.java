package com.example.TeamManagementSystem.configuration.security.service.impl;

import com.example.TeamManagementSystem.configuration.security.service.JWTService;
import com.example.TeamManagementSystem.configuration.security.userAuthDataConfiguration.AppUserDetails;
import com.example.TeamManagementSystem.configuration.security.userAuthDataConfiguration.AppUserDetailsService;
import com.example.TeamManagementSystem.domain.UserCredentials;

import com.example.TeamManagementSystem.repository.JWTTokenRepository;
import com.tms.dao.tmsdao.domain.JWTTokenEntity;
import com.tms.dao.tmsdao.domain.enumTypes.auth.Authority;
import com.tms.dao.tmsdao.domain.enumTypes.auth.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.util.Strings;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

@Lazy
@Service
public class JWTServiceImpl implements JWTService {

    private final String AUTHORIZATION = "Authorization";

    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private int expirationPeriod;

    @Autowired
    private JWTTokenRepository jwtTokenRepository;
    @Autowired
    private AppUserDetailsService appUserDetailsService;
    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public String generateToken(AppUserDetails appUserDetails) {
        Date expiration = Date.from(Instant.from(LocalDate.now().plusDays(expirationPeriod).atStartOfDay(ZoneId.systemDefault())));
        Claims claims = Jwts.claims().setSubject(appUserDetails.getUsername());
        claims.put("userRole", appUserDetails.getAuthorities());
        claims.setExpiration(expiration);
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        jwtTokenRepository.save(new JWTTokenEntity(token));
        return token;
    }

    @Override
    public void logout(HttpServletRequest httpServletRequest) throws AuthenticationException {
        String token = getTokenFromRequest(httpServletRequest);
        jwtTokenRepository.deleteByJwtToken(token);
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
    }

    @Override
    public String login(UserCredentials creds) throws AuthenticationException {
        AppUserDetails userDetails = (AppUserDetails) appUserDetailsService.loadUserByUsername(creds.getLogin());
        if (Objects.nonNull(userDetails) && userDetails.nonNullProperties() && passwordEncoder.matches(creds.getPassword(), userDetails.getPassword())) {
            return generateToken(userDetails);
        }
        throw new AuthenticationException("Unknown user");
    }

    @Override
    public boolean isValid(String token) {
        JWTTokenEntity tokenFromDB = jwtTokenRepository.findByJwtToken(token);
        return Objects.nonNull(tokenFromDB) && new Date(System.currentTimeMillis()).before(getExpiration(token));

    }

    @Override
    public String getTokenFromRequest(HttpServletRequest httpServletRequest) throws AuthenticationException {
        String token = httpServletRequest.getHeader(AUTHORIZATION);
        if (Objects.nonNull(token) && Strings.isNotBlank(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        throw new AuthenticationException("Bearer token not found");
    }

    @Override
    public String getPrincipal(String token) {
        return (String) getClaims(token).getSubject();
    }


    @Override
    public Authority getAuthority(String token) {
        return getClaims(token).get("authority", Authority.class);
    }

    @Override
    public UserRole getUserRole(String token) {
        return UserRole.valueOf(getClaims(token).get("userRole", String.class));
    }

    @Override
    public Date getExpiration(String token) {
        return getClaims(token).getExpiration();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
