package com.example.petProject.controller.rest;

import com.example.petProject.configuration.security.service.JWTService;
import com.example.petProject.model.UserCredentials;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class JWTAuthController {


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
