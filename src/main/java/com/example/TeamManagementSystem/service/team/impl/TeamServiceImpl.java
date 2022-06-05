package com.example.TeamManagementSystem.service.team.impl;

import com.example.TeamManagementSystem.domain.dto.TeamDTO;
import com.example.TeamManagementSystem.domain.dto.TeamMemberDTO;
import com.example.TeamManagementSystem.domain.entity.TeamEntity;
import com.example.TeamManagementSystem.domain.entity.TeamMemberEntity;
import com.example.TeamManagementSystem.mapper.OrikaBeanMapper;
import com.example.TeamManagementSystem.repository.TeamRepository;
import com.example.TeamManagementSystem.service.team.TeamMemberService;
import com.example.TeamManagementSystem.service.team.TeamService;
import com.example.TeamManagementSystem.service.user.UserService;
import com.example.TeamManagementSystem.util.AuthorizationUtils;
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
