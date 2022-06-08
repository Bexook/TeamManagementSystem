package com.example.TeamManagementSystem.service.model;

import com.tms.common.domain.dto.TeamDTO;
import com.tms.common.domain.dto.TeamMemberDTO;

import java.util.List;

public interface TeamService {

    void addMembersToTeam(final List<TeamMemberDTO> team, final Long teamId);

    void createTeam(final String projectName);

    List<TeamDTO> getAllTeams();

    TeamDTO findForAuthorizedUser();

    TeamDTO getById(final Long teamId);


}