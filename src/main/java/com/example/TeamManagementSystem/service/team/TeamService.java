package com.example.TeamManagementSystem.service.team;

import com.example.TeamManagementSystem.domain.dto.TeamDTO;
import com.example.TeamManagementSystem.domain.dto.TeamMemberDTO;

import java.util.List;

public interface TeamService {

    void addMembersToTeam(final List<TeamMemberDTO> team, final Long teamId);

    void createTeam(final String projectName);

    List<TeamDTO> getAllTeams();

    TeamDTO findForAuthorizedUser();

    TeamDTO getById(final Long teamId);


}
