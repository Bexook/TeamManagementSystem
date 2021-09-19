package com.example.TeamManagementSystem.controller.mvc;

import com.example.TeamManagementSystem.domain.dto.UserRegisterDTO;
import com.example.TeamManagementSystem.service.model.TeamMemberService;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class AuthenticationController {

    @Autowired
    private TeamMemberService teamMemberService;

    @GetMapping("/register")
    public void registerPage(@RequestBody UserRegisterDTO userRegisterDTO, HttpServletResponse httpServletResponse) throws IOException, BadHttpRequest {
        teamMemberService.registerTeamMember(userRegisterDTO);
    }

}
