package com.example.TeamManagementSystem.service.model.impl;

import com.tms.common.annotation.Approver;
import com.tms.common.annotation.ChangeRequest;
import com.tms.common.mapper.OrikaBeanMapper;
import com.example.TeamManagementSystem.repository.TeamMemberRepository;
import com.example.TeamManagementSystem.service.model.TeamMemberService;
import com.tms.common.security.service.UserService;
import com.tms.common.changeRequestDomain.enumTypes.OperationType;
import com.tms.common.domain.TeamMemberEntity;
import com.tms.common.domain.dto.TeamMemberDTO;
import com.tms.common.domain.dto.UserDTO;
import com.tms.common.domain.enumTypes.TeamMemberRole;
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
    public boolean addNew(@NonNull TeamMemberEntity teamMember) {
        UserDTO userDTO = userService.findByEmail(teamMember.getUserEntity().getEmail());
        return Objects.nonNull(teamMemberRepository.findByDTOUserId(userDTO.getId()));
    }

    @Override
    @ChangeRequest(operationType = OperationType.CREATE)
    public void registerTeamMember(final TeamMemberEntity teamMember) throws BadHttpRequest {
        teamMemberRepository.save(teamMember);
    }


    @Override
    public List<TeamMemberDTO> findAll() {
        return mapper.mapAsList(teamMemberRepository.findAll(), TeamMemberDTO.class);
    }
}
