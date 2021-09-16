package com.example.TeamManagementSystem.configuration.security.provider;

import joptsimple.internal.Strings;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Objects;

@Log4j2
public class CustomAuthenticationProvider implements AuthenticationProvider {


    private final UserDetailsService userService;

    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(UserDetailsService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        log.info("====================================== CUSTOM AUTH PROVIDER ======================================");

        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (Strings.isNullOrEmpty(email)) throw new BadCredentialsException("Username is not specified");
        if (Strings.isNullOrEmpty(password)) throw new BadCredentialsException("Password is not specified");

        UserDetails user = userService.loadUserByUsername(email);
        boolean isPasswordCorrect = false;
        if (Objects.nonNull(user)) {
            isPasswordCorrect = passwordEncoder.matches(password, user.getPassword());
        }

        if (!isPasswordCorrect) throw new BadCredentialsException("Bad credentials");

        Collection<? extends GrantedAuthority> authorityList = user.getAuthorities();


        return new UsernamePasswordAuthenticationToken(user, password, authorityList);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
