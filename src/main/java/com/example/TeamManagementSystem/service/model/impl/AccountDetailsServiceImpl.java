package com.example.TeamManagementSystem.service.model.impl;

import com.example.TeamManagementSystem.service.model.AccountDetailsService;
import com.example.TeamManagementSystem.service.model.UserService;
import com.example.TeamManagementSystem.model.dto.TeamMemberDTO;
import com.example.TeamManagementSystem.model.entity.TeamMemberEntity;
import com.example.TeamManagementSystem.service.model.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<TeamMemberEntity> getAccountDetails() {
        return teamMemberService.findAll();
    }
}
