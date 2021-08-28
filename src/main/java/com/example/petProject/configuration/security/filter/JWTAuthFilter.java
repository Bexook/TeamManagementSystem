package com.example.petProject.configuration.security.filter;

import com.example.petProject.configuration.security.service.JWTService;
import com.example.petProject.configuration.security.userAuthDataConfiguration.AppUserDetails;
import com.example.petProject.configuration.security.userAuthDataConfiguration.AppUserDetailsService;
import io.jsonwebtoken.JwtException;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtTokenService;
    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @Override
    @SneakyThrows
    @ResponseStatus(HttpStatus.UNAUTHORIZED)  // 403
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) {
        if (request.getRequestURI().endsWith("/login") || request.getRequestURI().endsWith("/logout")) {
            filterChain.doFilter(request, response);
        } else {
            final String token = jwtTokenService.getTokenFromRequest(request);

            if (jwtTokenService.isValid(token)) {
                AppUserDetails appUserDetails = (AppUserDetails) appUserDetailsService.loadUserByUsername(jwtTokenService.getPrincipal(token));
                UsernamePasswordAuthenticationToken t =
                        new UsernamePasswordAuthenticationToken(appUserDetails, null, null);
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(t);
                filterChain.doFilter(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            }
        }

    }

}
