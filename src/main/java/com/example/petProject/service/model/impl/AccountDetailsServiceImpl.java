package com.example.petProject.service.model.impl;

import com.example.petProject.model.entity.TeamMemberEntity;
import com.example.petProject.service.model.AccountDetailsService;
import com.example.petProject.service.model.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountDetailsServiceImpl implements AccountDetailsService {
    @Autowired
    private TeamMemberService teamMemberService;


    @Override
    public String getAccountDetailsForSpecificAcc(Long id) {
        return teamMemberService.findById(id).getName();
    }

    @Override
    public List<TeamMemberEntity> getAccountDetails() {
        return teamMemberService.findAll();
    }
}
