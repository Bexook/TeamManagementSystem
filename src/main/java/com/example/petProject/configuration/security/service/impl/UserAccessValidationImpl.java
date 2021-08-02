package com.example.petProject.configuration.security.service.impl;

import com.example.petProject.configuration.security.service.UserAccessValidation;
import com.example.petProject.configuration.security.userAuthDataConfiguration.AppUserDetails;
import com.example.petProject.model.enumTypes.auth.AccessType;
import com.example.petProject.model.enumTypes.auth.Authority;
import com.example.petProject.model.enumTypes.auth.UserRole;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("userAccessValidation")
public class UserAccessValidationImpl implements UserAccessValidation {

    @Override
    public boolean isAllowedAccessType(String accessType) {
        AppUserDetails user = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUserRole().getAccessType() == AccessType.valueOf(accessType);
    }

    @Override
    public boolean hasAuthority(String authority) {
        AppUserDetails user = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUserRole().getAuthority().contains(Authority.valueOf(authority));
    }

    @Override
    public boolean hasRole(String userRole) {
        AppUserDetails user = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUserRole() == UserRole.valueOf(userRole);
    }
}
