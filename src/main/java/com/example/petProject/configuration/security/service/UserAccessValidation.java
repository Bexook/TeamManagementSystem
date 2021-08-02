package com.example.petProject.configuration.security.service;

public interface UserAccessValidation {


    boolean isAllowedAccessType(String accessType);

    boolean hasAuthority(String authority);

    boolean hasRole(String userRole);


}
