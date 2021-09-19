package com.example.TeamManagementSystem.service.model.impl;

import com.example.TeamManagementSystem.changeRequestFeature.annotation.Approver;
import com.example.TeamManagementSystem.changeRequestFeature.annotation.ChangeRequest;
import com.example.TeamManagementSystem.changeRequestFeature.model.enumTypes.OperationType;
import com.example.TeamManagementSystem.domain.dto.UserDTO;
import com.example.TeamManagementSystem.domain.entity.UserEntity;
import com.example.TeamManagementSystem.domain.enumTypes.auth.UserRole;
import com.example.TeamManagementSystem.mapper.OrikaBeanMapper;
import com.example.TeamManagementSystem.repository.TeamMemberRepository;
import com.example.TeamManagementSystem.service.model.UserService;
import com.example.TeamManagementSystem.domain.dto.TeamMemberDTO;
import com.example.TeamManagementSystem.domain.dto.UserRegisterDTO;
import com.example.TeamManagementSystem.domain.entity.TeamMemberEntity;
import com.example.TeamManagementSystem.domain.enumTypes.TeamMemberRole;
import com.example.TeamManagementSystem.service.model.TeamMemberService;
import javassist.tools.web.BadHttpRequest;
import lombok.NonNull;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Approver(repository = TeamMemberRepository.class)
public class TeamMemberServiceImpl implements TeamMemberService {

    @Autowired
    private TeamMemberRepository teamMemberRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private OrikaBeanMapper mapper;


    @Override
    public TeamMemberDTO findById(@NonNull Long id) {
        return mapper.map(teamMemberRepository.findById(id).orElseThrow(), TeamMemberDTO.class);
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
    public List<TeamMemberDTO> findByTeamRole(@NonNull TeamMemberRole teamMemberRole) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("filterByRole").setParameter("role", teamMemberRole.toString());
        List<TeamMemberEntity> teamMemberEntities = teamMemberRepository.findAll();
        session.disableFilter("filterByRole");
        return mapper.mapAsList(teamMemberEntities, TeamMemberDTO.class);
    }


    @Override
    @Transactional
    @ChangeRequest(operationType = OperationType.DELETE)
    public void delete(@NonNull Long id) {
        teamMemberRepository.deleteById(id);
    }


    @Override
    @Transactional
    @ChangeRequest(operationType = OperationType.UPDATE)
    public void update(@NonNull TeamMemberDTO teamMemberDTO) {
        teamMemberRepository.save(mapper.map(teamMemberDTO, TeamMemberEntity.class));
    }


    @Override
    @ChangeRequest(operationType = OperationType.UPDATE)
    public boolean addNew(@NonNull TeamMemberDTO teamMemberDTO) {
        teamMemberRepository.save(mapper.map(teamMemberDTO, TeamMemberEntity.class));
        UserDTO userDTO = userService.getUserById(teamMemberDTO.getUserId());
        return Objects.nonNull(teamMemberRepository.findByDTOUserId(userDTO.getId()));
    }

    @Override
    @ChangeRequest(operationType = OperationType.CREATE)
    public void registerTeamMember(final UserRegisterDTO userRegisterDTO) throws BadHttpRequest {
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
        userEntity.setCreatedAt(new Date());

        TeamMemberDTO teamMemberDTO = new TeamMemberDTO();
        teamMemberDTO.setName(userRegisterDTO.getUsername());
        teamMemberDTO.setMemberRole(userRegisterDTO.getTeamMemberRole());
        if (!this.addNew(teamMemberDTO)) {
            throw new BadHttpRequest();
        }
        teamMemberRepository.save(mapper.map(teamMemberDTO, TeamMemberEntity.class));
    }


    @Override
    public List<TeamMemberDTO> findAll() {
        return mapper.mapAsList(teamMemberRepository.findAll(), TeamMemberDTO.class);
    }
}
