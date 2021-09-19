package com.example.TeamManagementSystem.util;

import com.example.TeamManagementSystem.configuration.security.userAuthDataConfiguration.AppUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthorizationUtils {

    public static String getCurrentUsername() {
        AppUserDetails userDetails = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

}
