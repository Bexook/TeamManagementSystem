package com.example.TeamManagementSystem.controller.rest;

import com.example.TeamManagementSystem.domain.UserCredentials;
import com.example.TeamManagementSystem.configuration.security.service.JWTService;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class JWTAuthResource {


    @Autowired
    private JWTService jwtService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserCredentials creds) throws AuthenticationException {
        return ResponseEntity.ok(jwtService.login(creds));
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest httpServletRequest) throws AuthenticationException {
        jwtService.logout(httpServletRequest);
    }

}
