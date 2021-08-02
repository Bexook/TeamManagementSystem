package com.example.petProject.service.model;

import com.example.petProject.model.dto.TeamMemberDTO;
import com.example.petProject.model.dto.UserRegisterDTO;
import com.example.petProject.model.entity.TeamMemberEntity;
import com.example.petProject.model.enumTypes.TeamMemberRole;
import javassist.tools.web.BadHttpRequest;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
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
