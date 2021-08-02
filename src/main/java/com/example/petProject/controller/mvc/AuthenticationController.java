package com.example.petProject.controller.mvc;

import com.example.petProject.model.dto.UserRegisterDTO;
import com.example.petProject.service.model.TeamMemberService;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@org.springframework.stereotype.Controller
public class AuthenticationController {

    @Autowired
    private TeamMemberService teamMemberService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    @GetMapping("/logout")
    public void logout(HttpServletResponse httpServletResponse) throws IOException {
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        httpServletResponse.sendRedirect("/login");
    }


    @GetMapping("/register")
    public String registerPage(WebRequest webRequest, Model model) {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        model.addAttribute("user", userRegisterDTO);
        return "register";
    }

    //TODO teamMember register view and registration

    @PostMapping("/register")
    public void registerUser(@ModelAttribute("user") UserRegisterDTO userRegisterDTO,
                             HttpServletResponse httpServletResponse, Model model) throws IOException, BadHttpRequest {
        teamMemberService.registerTeamMember(userRegisterDTO);
    }

}
