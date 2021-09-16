package com.example.TeamManagementSystem.model;

import com.example.TeamManagementSystem.model.entity.UserEntity;
import com.example.TeamManagementSystem.model.entity.TeamMemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetails {

    private UserEntity userEntity;
    private TeamMemberEntity teamMemberEntity;
}
