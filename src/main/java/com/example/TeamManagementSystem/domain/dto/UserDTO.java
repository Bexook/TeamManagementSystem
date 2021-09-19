package com.example.TeamManagementSystem.domain.dto;

import com.example.TeamManagementSystem.domain.entity.BaseEntity;
import com.example.TeamManagementSystem.domain.enumTypes.auth.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends BaseEntity {

    private Long id;
    private String email;
    private String password;
    private boolean isActive;
    private boolean isEnable;
    private boolean isExpired;
    private UserRole userRole;
    private boolean isCredentialsExpired;
    private boolean isEmailVerified;

}
