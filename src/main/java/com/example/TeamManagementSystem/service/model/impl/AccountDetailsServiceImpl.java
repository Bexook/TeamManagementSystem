package com.example.TeamManagementSystem.service.model.impl;

import com.example.TeamManagementSystem.domain.dto.UserDTO;
import com.example.TeamManagementSystem.service.model.AccountDetailsService;
import com.example.TeamManagementSystem.service.model.UserService;
import com.example.TeamManagementSystem.domain.dto.TeamMemberDTO;
import com.example.TeamManagementSystem.domain.entity.TeamMemberEntity;
import com.example.TeamManagementSystem.service.model.TeamMemberService;
import com.example.TeamManagementSystem.util.AuthorizationUtils;
import lombok.SneakyThrows;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.SystemException;
import java.util.List;

@Service
public class AccountDetailsServiceImpl implements AccountDetailsService {
    @Autowired
    private TeamMemberService teamMemberService;
    @Autowired
    private UserService userService;


    @Override
    public TeamMemberDTO getAccountDetailsForSpecificAcc(String userEmail) {
        return teamMemberService.findByUserId(userService.findByEmail(userEmail).getId());
    }

    @Override
    public List<TeamMemberDTO> getAccountDetails() {
        return teamMemberService.findAll();
    }

    @Override
    public UserDTO getUserDetails() {
        return userService.findByEmail(AuthorizationUtils.getCurrentUsername());
    }

    @Override
    @SneakyThrows
    public UserDTO updateUserDetails(UserDTO userDTO) {
        if (AuthorizationUtils.getCurrentUsername().equals(userDTO.getEmail())) {
            userService.update(userDTO);
            return userService.findByEmail(userDTO.getEmail());
        }
        throw new SystemException(403);
    }
}
