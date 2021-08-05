package com.example.petProject.controller.mvc;

import com.example.petProject.configuration.security.userAuthDataConfiguration.AppUserDetails;
import com.example.petProject.model.dto.TeamMemberDTO;
import com.example.petProject.service.model.AccountDetailsService;
import com.example.petProject.service.model.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountOverviewController {

    @Autowired
    private AccountDetailsService accountDetailsService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    @PreAuthorize("@userAccessValidation.hasRole('USER','ADMIN')")
    public String getAccountDetails(Model model) {
        AppUserDetails appUserDetails = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TeamMemberDTO teamMemberDTO = accountDetailsService.getAccountDetailsForSpecificAcc(appUserDetails.getUsername());
        model.addAttribute("authenticatedUser", teamMemberDTO);
        return "account-overview";
    }


    @PostMapping("/del")
    @ResponseBody
    @PreAuthorize("@userAccessValidation.hasRole('USER','ADMIN')")
    public void getAccountDetailsDel() {

        userService.deleteById(1L);
    }

}
