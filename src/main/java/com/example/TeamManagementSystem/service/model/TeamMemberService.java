package com.example.TeamManagementSystem.service.model;


import com.tms.common.domain.TeamMemberEntity;
import com.tms.common.domain.dto.TeamMemberDTO;
import com.tms.common.domain.enumTypes.TeamMemberRole;
import javassist.tools.web.BadHttpRequest;
import lombok.NonNull;

import java.io.IOException;
import java.util.List;

public interface TeamMemberService {

    TeamMemberDTO findById(Long id);

    List<TeamMemberDTO> getForTeam(final Long teamId);

    TeamMemberDTO findByUserId(Long id);

    List<TeamMemberDTO> findByTeamRole(TeamMemberRole teamMemberRole);

    List<TeamMemberDTO> findAllTeamMembers();

    void delete(Long id);

    void update(TeamMemberDTO teamMemberDTO);

    void addNew(@NonNull TeamMemberDTO teamMember, @NonNull final String userEmail);

    void registerTeamMember(final TeamMemberEntity teamMember) throws IOException, BadHttpRequest;

}
