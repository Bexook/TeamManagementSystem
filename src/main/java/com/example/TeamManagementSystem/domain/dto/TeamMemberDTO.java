package com.example.TeamManagementSystem.domain.dto;

import com.example.TeamManagementSystem.domain.entity.UserEntity;
import com.example.TeamManagementSystem.domain.enumTypes.TeamMemberRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamMemberDTO {

    private Long id;
    private String name;
    private TeamMemberRole memberRole;
    private UserEntity userEntity;
}
