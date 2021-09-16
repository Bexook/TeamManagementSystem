package com.example.TeamManagementSystem.service.model;

import com.example.TeamManagementSystem.model.dto.TeamMemberDTO;
import com.example.TeamManagementSystem.model.dto.UserRegisterDTO;
import com.example.TeamManagementSystem.model.entity.TeamMemberEntity;
import com.example.TeamManagementSystem.model.enumTypes.TeamMemberRole;
import javassist.tools.web.BadHttpRequest;

import java.io.IOException;
import java.util.List;

public interface TeamMemberService {


    TeamMemberEntity findById(Long id);

    TeamMemberDTO findDTOById(Long id);

    TeamMemberDTO findByUserId(Long id);

    List<TeamMemberEntity> findByTeamRole(TeamMemberRole teamMemberRole);

    List<TeamMemberEntity> findAll();

    void delete(Long id);

    void update(TeamMemberEntity teamMemberEntity);

    boolean addNew(TeamMemberEntity entity);

    void registerTeamMember(final UserRegisterDTO userRegisterDTO) throws IOException, BadHttpRequest;

}
