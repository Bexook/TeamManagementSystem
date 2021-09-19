package com.example.TeamManagementSystem.domain;

import com.example.TeamManagementSystem.domain.entity.UserEntity;
import com.example.TeamManagementSystem.domain.entity.TeamMemberEntity;
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
