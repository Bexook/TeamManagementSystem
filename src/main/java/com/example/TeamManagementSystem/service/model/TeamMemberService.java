package com.example.TeamManagementSystem.service.model;

import com.example.TeamManagementSystem.domain.dto.TeamMemberDTO;
import com.example.TeamManagementSystem.domain.dto.UserRegisterDTO;
import com.example.TeamManagementSystem.domain.entity.TeamMemberEntity;
import com.example.TeamManagementSystem.domain.enumTypes.TeamMemberRole;
import javassist.tools.web.BadHttpRequest;

import java.io.IOException;
import java.util.List;

public interface TeamMemberService {

    TeamMemberDTO findById(Long id);

    TeamMemberDTO findDTOById(Long id);

    TeamMemberDTO findByUserId(Long id);

    List<TeamMemberDTO> findByTeamRole(TeamMemberRole teamMemberRole);

    List<TeamMemberDTO> findAll();

    void delete(Long id);

    void update(TeamMemberDTO teamMemberDTO);

    boolean addNew(TeamMemberEntity entity);

    void registerTeamMember(final TeamMemberEntity teamMember) throws IOException, BadHttpRequest;

}
