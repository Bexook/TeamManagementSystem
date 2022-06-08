package com.example.TeamManagementSystem.controller.rest;


import com.example.TeamManagementSystem.service.model.TeamMemberService;
import com.example.TeamManagementSystem.service.model.TeamService;
import com.tms.common.domain.dto.TeamDTO;
import com.tms.common.domain.dto.TeamMemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team-management")
public class TeamManagementResource {

    @Autowired
    private TeamService teamService;
    @Autowired
    private TeamMemberService teamMemberService;

    @GetMapping("/get/team-members")
    public ResponseEntity<List<TeamMemberDTO>> getAllTeamMembers() {
        return ResponseEntity.ok().body(teamMemberService.findAllTeamMembers());
    }


    @PostMapping("/create/team-member")
    @PreAuthorize("@userAccessValidation.hasRole('ADMIN')")
    public void createTeam(@RequestBody final TeamMemberDTO teamMemberDTO, @RequestParam("userEmail")final String userEmail) {
        teamMemberService.addNew(teamMemberDTO, userEmail);
    }


    @GetMapping("/get/team")
    public ResponseEntity<TeamDTO> getAllForUser() {
        return ResponseEntity.ok().body(teamService.findForAuthorizedUser());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TeamDTO> getById(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok().body(teamService.getById(id));
    }

    @GetMapping("/get/all-teams")
    @PreAuthorize("@userAccessValidation.hasRole('ADMIN')")
    public ResponseEntity<List<TeamDTO>> getAllTeams() {
        return ResponseEntity.ok().body(teamService.getAllTeams());
    }


    @PostMapping("/create-team")
    @PreAuthorize("@userAccessValidation.hasRole('ADMIN')")
    public void createTeam(@RequestParam final String projectName) {
        teamService.createTeam(projectName);
    }

    @PostMapping("/update")
    @PreAuthorize("@userAccessValidation.hasRole('ADMIN')")
    public void updateTeam(@RequestBody final List<TeamMemberDTO> teamMembers, @RequestParam("teamId") Long teamId) {
        teamService.addMembersToTeam(teamMembers, teamId);
    }


}