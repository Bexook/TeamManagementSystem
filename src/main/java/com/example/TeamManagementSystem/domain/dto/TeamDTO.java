package com.example.TeamManagementSystem.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class TeamDTO {
    private Long id;
    private String projectName;
    private List<TeamMemberDTO> teamMembers;
}
