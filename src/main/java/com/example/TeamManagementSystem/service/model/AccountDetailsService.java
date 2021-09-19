package com.example.TeamManagementSystem.service.model;

import com.example.TeamManagementSystem.domain.dto.TeamMemberDTO;
import com.example.TeamManagementSystem.domain.entity.TeamMemberEntity;

import java.util.List;

public interface AccountDetailsService {

    TeamMemberDTO getAccountDetailsForSpecificAcc(String userEmail);

    List<TeamMemberEntity> getAccountDetails();


}
