package com.example.TeamManagementSystem.domain;

import com.tms.dao.tmsdao.domain.TeamMemberEntity;
import com.tms.dao.tmsdao.domain.UserEntity;
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
