package com.example.TeamManagementSystem.configuration.security.service;

import com.example.TeamManagementSystem.configuration.security.userAuthDataConfiguration.AppUserDetails;
import com.example.TeamManagementSystem.domain.UserCredentials;
import com.example.TeamManagementSystem.domain.enumTypes.auth.AccessType;
import com.example.TeamManagementSystem.domain.enumTypes.auth.Authority;
import com.example.TeamManagementSystem.domain.enumTypes.auth.UserRole;
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

    Date getExpiration(String token);

}
