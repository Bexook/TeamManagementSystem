package com.example.TeamManagementSystem.service.model.impl;

import com.example.TeamManagementSystem.service.model.TeamMemberService;
import com.example.TeamManagementSystem.service.model.TeamService;
import com.tms.common.domain.TeamEntity;
import com.tms.common.domain.TeamMemberEntity;
import com.tms.common.domain.dto.TeamDTO;
import com.tms.common.domain.dto.TeamMemberDTO;
import com.tms.common.mapper.OrikaBeanMapper;
import com.tms.common.repository.TeamRepository;
import com.tms.common.security.service.UserService;
import com.tms.common.util.AuthorizationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {


    @Autowired
    private OrikaBeanMapper mapper;
    @Autowired
    private UserService userService;
    @Autowired
    private TeamMemberService teamMemberService;
    @Autowired
    private TeamRepository teamRepository;


    @Override
    public void addMembersToTeam(List<TeamMemberDTO> teamMembers, final Long teamId) {
        final TeamEntity entity = teamRepository.getById(teamId);
        entity.setTeamMembers(mapper.mapAsList(teamMembers, TeamMemberEntity.class));
        teamRepository.save(entity);
    }

    @Override
    public void createTeam(final String projectName) {
        final TeamEntity entity = new TeamEntity();
        entity.setProjectName(projectName);
        teamRepository.save(entity);
    }

    @Override
    public List<TeamDTO> getAllTeams() {
        return mapper.mapAsList(teamRepository.findAll(), TeamDTO.class);
    }

    @Override
    public TeamDTO findForAuthorizedUser() {
        return teamMemberService.findByUserId(userService.findByEmail(AuthorizationUtils.getCurrentUsername()).getId()).getTeam();
    }

    @Override
    public TeamDTO getById(Long teamId) {
        return mapper.map(teamRepository.getById(teamId), TeamDTO.class);
    }
}