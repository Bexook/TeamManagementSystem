package com.example.petProject.configuration.security.service;

import com.example.petProject.configuration.security.userAuthDataConfiguration.AppUserDetails;
import com.example.petProject.model.UserCredentials;
import com.example.petProject.model.enumTypes.auth.Authority;
import com.example.petProject.model.enumTypes.auth.UserRole;
import org.apache.tomcat.websocket.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public interface JWTService {

    String generateToken(AppUserDetails appUserDetails);

    void logout(HttpServletRequest httpServletRequest) throws AuthenticationException;

    String login(UserCredentials userCredentials) throws AuthenticationException;

    boolean isValid(String token);

    String getTokenFromRequest(HttpServletRequest httpServletRequest) throws AuthenticationException;

    String getPrincipal(String token);

    Authority getAuthority(String token);

    UserRole getUserRole(String token);

    Date getExpiration(String token);

}
