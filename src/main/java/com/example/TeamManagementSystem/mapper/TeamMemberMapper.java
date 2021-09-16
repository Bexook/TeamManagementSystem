package com.example.TeamManagementSystem.mapper;

import com.example.TeamManagementSystem.model.dto.TeamMemberDTO;
import com.example.TeamManagementSystem.model.entity.TeamMemberEntity;
import org.mapstruct.Mapper;

@Mapper
public interface TeamMemberMapper {

    TeamMemberDTO mapToTeamMemberDTO(TeamMemberEntity teamMemberEntity);

}
