package com.example.petProject.service.model;

import com.example.petProject.model.dto.TeamMemberDTO;
import com.example.petProject.model.entity.TeamMemberEntity;

import java.util.List;

public interface AccountDetailsService {

    TeamMemberDTO getAccountDetailsForSpecificAcc(String userEmail);

    List<TeamMemberEntity> getAccountDetails();


}
