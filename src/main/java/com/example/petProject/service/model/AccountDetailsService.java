package com.example.petProject.service.model;

import com.example.petProject.model.entity.TeamMemberEntity;

import java.util.List;

public interface AccountDetailsService {

    String getAccountDetailsForSpecificAcc(Long id);

    List<TeamMemberEntity> getAccountDetails();


}
