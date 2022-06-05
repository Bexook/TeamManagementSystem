package com.example.TeamManagementSystem.service.user;

import com.example.TeamManagementSystem.domain.dto.TeamMemberDTO;
import com.example.TeamManagementSystem.domain.dto.UserDTO;
import com.example.TeamManagementSystem.domain.dto.UserRegisterDTO;

import java.util.List;

public interface AccountDetailsService {

    TeamMemberDTO getAccountDetailsForSpecificAcc(String userEmail);

    List<TeamMemberDTO> getAccountDetails();

    UserDTO getUserDetails();

    UserDTO updateUserDetails(UserDTO userDTO);

    void createNewAccount(UserRegisterDTO userRegisterDTO);



}
