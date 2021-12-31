package com.example.TeamManagementSystem.service.model;

import com.tms.dao.tmsdao.domain.dto.TeamMemberDTO;
import com.tms.dao.tmsdao.domain.dto.UserDTO;
import com.tms.dao.tmsdao.domain.dto.UserRegisterDTO;

import java.util.List;


public interface AccountDetailsService {

    TeamMemberDTO getAccountDetailsForSpecificAcc(String userEmail);

    List<TeamMemberDTO> getAccountDetails();

    UserDTO getUserDetails();

    UserDTO updateUserDetails(UserDTO userDTO);

    void createNewAccount(UserRegisterDTO userRegisterDTO);



}
