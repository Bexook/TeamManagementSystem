package com.example.petProject.controller.mvc;

import com.example.petProject.configuration.security.userAuthDataConfiguration.AppUserDetails;
import com.example.petProject.model.dto.TeamMemberDTO;
import com.example.petProject.service.model.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountOverviewController {

    @Autowired
    private AccountDetailsService accountDetailsService;

    @GetMapping("/")
    @PreAuthorize("@userAccessValidation.hasRole('USER','ADMIN')")
    public ResponseEntity<TeamMemberDTO> getAccountDetails() {
        AppUserDetails appUserDetails = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TeamMemberDTO teamMemberDTO = accountDetailsService.getAccountDetailsForSpecificAcc(appUserDetails.getUsername());
        return ResponseEntity.ok().body(teamMemberDTO);
    }
}
