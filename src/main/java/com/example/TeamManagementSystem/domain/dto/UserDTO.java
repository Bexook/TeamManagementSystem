package com.example.TeamManagementSystem.domain.dto;

import com.example.TeamManagementSystem.domain.entity.BaseEntity;
import com.example.TeamManagementSystem.domain.enumTypes.auth.AccessType;
import com.example.TeamManagementSystem.domain.enumTypes.auth.Authority;
import com.example.TeamManagementSystem.domain.enumTypes.auth.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends BaseDTO {

    private Long id;
    private String email;
    private String password;
    private boolean isActive;
    private boolean isEnable;
    private boolean isExpired;
    private UserRole userRole;
    private boolean isCredentialsExpired;
    private boolean isEmailVerified;
    private AccessType accessType;
    private Set<Authority> authorities;

}
