package com.example.TeamManagementSystem.domain.dto;

import com.example.TeamManagementSystem.domain.enumTypes.TeamMemberRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TeamMemberDTO {

    private Long id;
    private String name;
    private TeamMemberRole memberRole;
    private UserDTO user;
    private TeamDTO team;

    public TeamMemberDTO(Long id, String name, TeamMemberRole teamMemberRole, UserDTO user) {
        this.id = id;
        this.name = name;
        this.memberRole = teamMemberRole;
        this.user = user;
    }
}
