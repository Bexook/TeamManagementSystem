package com.example.TeamManagementSystem.mapper;

import com.example.TeamManagementSystem.domain.dto.TeamMemberDTO;
import com.example.TeamManagementSystem.domain.entity.TeamMemberEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface TeamMemberMapper {

    TeamMemberDTO mapToTeamMemberDTO(TeamMemberEntity teamMemberEntity);

}
