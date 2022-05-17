package com.example.TeamManagementSystem.service.model;

import com.tms.common.domain.dto.TeamMemberDTO;
import com.tms.common.domain.dto.UserDTO;
import com.tms.common.domain.dto.UserRegisterDTO;

import java.util.List;


public interface AccountDetailsService {

    TeamMemberDTO getAccountDetailsForSpecificAcc(String userEmail);

    List<TeamMemberDTO> getAccountDetails();

    UserDTO getUserDetails();

    UserDTO updateUserDetails(UserDTO userDTO);

    void createNewAccount(UserRegisterDTO userRegisterDTO);



}
