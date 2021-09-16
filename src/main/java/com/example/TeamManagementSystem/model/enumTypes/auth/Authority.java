package com.example.TeamManagementSystem.model.enumTypes.auth;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    READ("READ"),
    UPDATE("UPDATE"),
    CREATE("CREATE"),
    DELETE("DELETE"),
    APPROVE("APPROVE");

    private final String authority;

    Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
