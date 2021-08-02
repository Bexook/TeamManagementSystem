package com.example.petProject.model;

import com.example.petProject.model.entity.TeamMemberEntity;
import com.example.petProject.model.entity.UserEntity;
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
