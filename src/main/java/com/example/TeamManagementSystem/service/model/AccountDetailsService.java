package com.example.TeamManagementSystem.service.model;

import com.example.TeamManagementSystem.model.dto.TeamMemberDTO;
import com.example.TeamManagementSystem.model.entity.TeamMemberEntity;

import java.util.List;

public interface AccountDetailsService {

    TeamMemberDTO getAccountDetailsForSpecificAcc(String userEmail);

    List<TeamMemberEntity> getAccountDetails();


}
