package com.example.TeamManagementSystem.service.team;

import com.example.TeamManagementSystem.domain.dto.TeamMemberDTO;
import com.example.TeamManagementSystem.domain.dto.UserRegisterDTO;
import com.example.TeamManagementSystem.domain.entity.TeamMemberEntity;
import com.example.TeamManagementSystem.domain.enumTypes.TeamMemberRole;
import javassist.tools.web.BadHttpRequest;

import java.io.IOException;
import java.util.List;

public interface TeamMemberService {

    TeamMemberDTO findById(Long id);

    List<TeamMemberDTO> getForTeam(final Long teamId);

    TeamMemberDTO findByUserId(Long id);

    List<TeamMemberDTO> findByTeamRole(TeamMemberRole teamMemberRole);

    List<TeamMemberDTO> findAllTeamMembers();

    void delete(Long id);

    void update(TeamMemberDTO teamMemberDTO);

    void addNew(TeamMemberDTO teamMember, final String userEmail);

    void registerTeamMember(final TeamMemberEntity teamMember) throws IOException, BadHttpRequest;

}
