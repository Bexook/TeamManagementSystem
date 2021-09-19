package com.example.TeamManagementSystem.domain.dto;

import com.example.TeamManagementSystem.domain.enumTypes.TeamMemberRole;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamMemberDTO {

    private Long id;
    private String name;
    private TeamMemberRole memberRole;
    private Long userId;

    public TeamMemberDTO(Long id, String name, Integer memberRole, Long userId) {
        this.id = id;
        this.name = name;
        this.memberRole = TeamMemberRole.getByCode(memberRole);
        this.userId = userId;
    }
}
