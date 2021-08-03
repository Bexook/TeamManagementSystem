package com.example.petProject.controller.mvc;

import com.example.petProject.configuration.security.userAuthDataConfiguration.AppUserDetails;
import com.example.petProject.model.dto.TeamMemberDTO;
import com.example.petProject.service.model.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountOverviewController {

    @Autowired
    private AccountDetailsService accountDetailsService;

    @GetMapping("/")
    @PreAuthorize("@userAccessValidation.hasRole('USER','ADMIN')")
    public String getAccountDetails(Model model) {
        AppUserDetails appUserDetails = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TeamMemberDTO teamMemberDTO = accountDetailsService.getAccountDetailsForSpecificAcc(appUserDetails.getUsername());
        model.addAttribute("authenticatedUser", teamMemberDTO);
        return "account-overview";
    }
}
