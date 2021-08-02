package com.example.petProject.service.model.impl;

import com.example.petProject.model.dto.TeamMemberDTO;
import com.example.petProject.model.dto.UserRegisterDTO;
import com.example.petProject.model.entity.TeamMemberEntity;
import com.example.petProject.model.entity.UserEntity;
import com.example.petProject.model.enumTypes.TeamMemberRole;
import com.example.petProject.model.enumTypes.auth.UserRole;
import com.example.petProject.repository.TeamMemberRepository;
import com.example.petProject.service.model.TeamMemberService;
import com.example.petProject.service.model.UserService;
import javassist.tools.web.BadHttpRequest;
import lombok.NonNull;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.DispatcherServlet;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class TeamMemberServiceImpl implements TeamMemberService {

    @Autowired
    private DispatcherServlet dispatcherServlet;
    @Autowired
    private TeamMemberRepository teamMemberRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public TeamMemberEntity findById(@NonNull Long id) {
        return teamMemberRepository.findById(id).orElseThrow();
    }

    @Override
    public TeamMemberDTO findDTOById(@NonNull Long id) {
        return Objects.requireNonNull(teamMemberRepository.findByDTOId(id));
    }

    @Override
    public TeamMemberDTO findByUserId(@NonNull Long id) {
        return Objects.requireNonNull(teamMemberRepository.findByDTOUserId(id));
    }

    @Override
    public List<TeamMemberEntity> findByTeamRole(@NonNull TeamMemberRole teamMemberRole) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("filterByRole").setParameter("role", teamMemberRole.toString());
        List<TeamMemberEntity> teamMemberEntities = teamMemberRepository.findAll();
        session.disableFilter("filterByRole");
        return teamMemberEntities;
    }

    @Transactional
    @Override
    public void delete(@NonNull Long id) {
        teamMemberRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void update(@NonNull TeamMemberEntity teamMemberEntity) {
        teamMemberRepository.save(teamMemberEntity);
    }

    @Transactional
    @Override
    public boolean addNew(@NonNull TeamMemberEntity entity) {
        teamMemberRepository.save(entity);
        return Objects.nonNull(teamMemberRepository.findByDTOUserId(userService.findByEmail(entity.getUserEntity().getEmail()).getId()));
    }

    @Override
    public void registerTeamMember(final UserRegisterDTO userRegisterDTO) throws IOException, BadHttpRequest {
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getRepeatPassword())) {
           throw new BadHttpRequest();
        }

        if (Strings.isBlank(userRegisterDTO.getEmail())) {
            throw new BadHttpRequest();
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userRegisterDTO.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        userEntity.setUserRole(UserRole.USER);
        userEntity.setCreatedDate(new Date());

        TeamMemberEntity teamMemberEntity = new TeamMemberEntity();
        teamMemberEntity.setUserEntity(userEntity);
        teamMemberEntity.setName(userRegisterDTO.getUsername());
        teamMemberEntity.setMemberRole(userRegisterDTO.getTeamMemberRole());
        if(!this.addNew(teamMemberEntity)) {
           throw new BadHttpRequest();
        }
    }


    @Override
    public List<TeamMemberEntity> findAll() {
        return teamMemberRepository.findAll();
    }
}
