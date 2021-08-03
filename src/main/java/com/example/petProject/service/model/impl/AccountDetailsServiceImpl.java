package com.example.petProject.service.model.impl;

import com.example.petProject.model.dto.TeamMemberDTO;
import com.example.petProject.model.entity.TeamMemberEntity;
import com.example.petProject.service.model.AccountDetailsService;
import com.example.petProject.service.model.TeamMemberService;
import com.example.petProject.service.model.UserService;
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
