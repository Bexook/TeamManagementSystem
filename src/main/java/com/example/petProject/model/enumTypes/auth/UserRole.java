package com.example.petProject.model.enumTypes.auth;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.List;

@Log4j2
public enum UserRole {
    ADMIN(AccessType.ALL, Authority.CREATE, Authority.DELETE, Authority.READ, Authority.UPDATE),
    USER(AccessType.USER, Authority.DELETE, Authority.READ),
    ANONYMOUS(AccessType.PUBLIC, Authority.READ);
    private final List<Authority> authority;
    private final AccessType accessType;

    UserRole(AccessType accessType, Authority... authority) {
        this.authority = Arrays.asList(authority);
        this.accessType = accessType;
    }

    public List<Authority> getAuthority() {
        return authority;
    }

    public AccessType getAccessType() {
        return accessType;
    }

    public UserRole getRoleByAccessType(AccessType accessType) {
        UserRole result = Arrays.stream(values()).filter(role -> role.getAccessType().equals(accessType)).findFirst().orElse(UserRole.ANONYMOUS);
        if (result == ANONYMOUS) {
            log.info("ANONYMOUS user access ");
        }
        return result;
    }



}
