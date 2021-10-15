package com.example.TeamManagementSystem.service.model;

import com.example.TeamManagementSystem.domain.dto.TeamMemberDTO;
import com.example.TeamManagementSystem.domain.dto.UserDTO;
import com.example.TeamManagementSystem.domain.dto.UserRegisterDTO;
import com.example.TeamManagementSystem.domain.entity.TeamMemberEntity;

import java.util.List;

public interface AccountDetailsService {

    TeamMemberDTO getAccountDetailsForSpecificAcc(String userEmail);

    List<TeamMemberDTO> getAccountDetails();

    UserDTO getUserDetails();

    UserDTO updateUserDetails(UserDTO userDTO);

    void createNewAccount(UserRegisterDTO userRegisterDTO);



}
