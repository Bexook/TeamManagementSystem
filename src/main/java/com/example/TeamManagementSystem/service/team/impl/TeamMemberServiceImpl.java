package com.example.TeamManagementSystem.service.team.impl;

import com.example.TeamManagementSystem.changeRequestFeature.annotation.Approver;
import com.example.TeamManagementSystem.changeRequestFeature.annotation.ChangeRequest;
import com.example.TeamManagementSystem.changeRequestFeature.domain.enumTypes.OperationType;
import com.example.TeamManagementSystem.domain.dto.TeamMemberDTO;
import com.example.TeamManagementSystem.domain.dto.UserDTO;
import com.example.TeamManagementSystem.domain.entity.TeamMemberEntity;
import com.example.TeamManagementSystem.domain.entity.UserEntity;
import com.example.TeamManagementSystem.domain.enumTypes.TeamMemberRole;
import com.example.TeamManagementSystem.mapper.OrikaBeanMapper;
import com.example.TeamManagementSystem.repository.TeamMemberRepository;
import com.example.TeamManagementSystem.service.team.TeamMemberService;
import com.example.TeamManagementSystem.service.user.UserService;
import javassist.tools.web.BadHttpRequest;
import lombok.NonNull;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Approver(repository = TeamMemberRepository.class, domainClass = TeamMemberEntity.class)
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
    public List<TeamMemberDTO> getForTeam(Long teamId) {
        return null;
    }

    @Override
    public TeamMemberDTO findById(@NonNull Long id) {
        return mapper.map(teamMemberRepository.findById(id).orElseThrow(), TeamMemberDTO.class);
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
    public void addNew(@NonNull TeamMemberDTO teamMember, @NonNull final String userEmail) {
        UserDTO userDTO = userService.findByEmail(userEmail);
        final TeamMemberEntity entity = mapper.map(teamMember, TeamMemberEntity.class);
        entity.setUser(mapper.map(userDTO, UserEntity.class));
        teamMemberRepository.flush();
        teamMemberRepository.save(entity);
    }

    @Override
    @ChangeRequest(operationType = OperationType.CREATE)
    public void registerTeamMember(final TeamMemberEntity teamMember) throws BadHttpRequest {
        teamMemberRepository.save(teamMember);
    }


    @Override
    @Transactional()
    public List<TeamMemberDTO> findAllTeamMembers() {
        final List<TeamMemberEntity> entities = teamMemberRepository.findAll();
        return mapper.mapAsList(entities, TeamMemberDTO.class);
    }
}
