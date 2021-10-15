package com.example.TeamManagementSystem.controller.rest;

import com.example.TeamManagementSystem.domain.AccountDetails;
import com.example.TeamManagementSystem.domain.dto.TeamMemberDTO;
import com.example.TeamManagementSystem.domain.dto.UserRegisterDTO;
import com.example.TeamManagementSystem.service.model.AccountDetailsService;
import com.example.TeamManagementSystem.util.AuthorizationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/account")
public class AccountServiceResource {


    @Autowired
    private AccountDetailsService accountDetailsService;


    @GetMapping("/")
    @PreAuthorize("isFullyAuthenticated()")
    public ResponseEntity<TeamMemberDTO> getAccountDetails() {
        return ResponseEntity.ok().body(accountDetailsService.getAccountDetailsForSpecificAcc(AuthorizationUtils.getCurrentUsername()));
    }

    @GetMapping("/list")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<String>> getAccountList() {
        return ResponseEntity.ok().body(accountDetailsService.getAccountDetails().stream().map(TeamMemberDTO::getName).collect(Collectors.toList()));
    }

    @PostMapping("/new")
    public void createAccount(@RequestBody UserRegisterDTO userRegisterDTO) {
        accountDetailsService.createNewAccount(userRegisterDTO);
    }

}
