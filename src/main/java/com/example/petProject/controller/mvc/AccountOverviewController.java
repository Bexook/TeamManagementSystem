package com.example.petProject.controller.mvc;

import com.example.petProject.model.AccountDetails;
import com.example.petProject.model.entity.TeamMemberEntity;
import com.example.petProject.service.model.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/account")
public class AccountOverviewController {


    @Autowired
    private AccountDetailsService accountDetailsService;

    @ResponseBody
    @GetMapping("/{id}")
    public String getAccountDetails(@PathVariable("id") Long id) {
        return accountDetailsService.getAccountDetailsForSpecificAcc(id);
    }


}
