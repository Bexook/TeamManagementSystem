package com.example.TeamManagementSystem.controller.rest;

import com.example.TeamManagementSystem.service.model.AccountDetailsService;
import com.tms.common.domain.dto.TeamMemberDTO;
import com.tms.common.domain.dto.UserDTO;
import com.tms.common.util.AuthorizationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountOverviewResource {

    @Autowired
    private AccountDetailsService accountDetailsService;

    @GetMapping("/")
    @PreAuthorize("@userAccessValidation.hasRole('USER','ADMIN')")
    public ResponseEntity<TeamMemberDTO> getAccountDetails() {
        return ResponseEntity.ok().body(accountDetailsService.getAccountDetailsForSpecificAcc(AuthorizationUtils.getCurrentUsername()));
    }

    @GetMapping("/settings")
    public ResponseEntity<UserDTO> getSettingsIfo() {
        return ResponseEntity.ok().body(accountDetailsService.getUserDetails());
    }

    @PostMapping("/settings")
    public ResponseEntity<UserDTO> updateSettings(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok().body(accountDetailsService.updateUserDetails(userDTO));
    }

}
