package com.example.TeamManagementSystem.configuration.security.userAuthDataConfiguration;

import com.example.TeamManagementSystem.model.entity.UserEntity;
import com.example.TeamManagementSystem.model.enumTypes.auth.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AppUserDetails implements UserDetails {

    private UserEntity userEntity;

    public AppUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userEntity.getUserRole().toString()));
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !userEntity.isExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return userEntity.isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !userEntity.isCredentialsExpired();
    }

    @Override
    public boolean isEnabled() {
        return userEntity.isEnable() && userEntity.isEmailVerified();
    }

    public UserRole getUserRole() {
        return userEntity.getUserRole();
    }
}
