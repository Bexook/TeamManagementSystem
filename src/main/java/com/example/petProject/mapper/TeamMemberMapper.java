package com.example.petProject.mapper;

import com.example.petProject.model.dto.TeamMemberDTO;
import com.example.petProject.model.entity.TeamMemberEntity;
import org.mapstruct.Mapper;

@Mapper
public interface TeamMemberMapper {

    TeamMemberDTO mapToTeamMemberDTO(TeamMemberEntity teamMemberEntity);

}
