package com.example.TeamManagementSystem.controller.rest;

import com.tms.common.security.service.UserService;
import com.tms.common.domain.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserServiceResource {


    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public void registerUser(@RequestBody final UserDTO user) {
        userService.registerUser(user);
    }

    @PostMapping("/delete/{id}")
    public void deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteById(id);
    }

    @GetMapping("/get")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam(name = "email") String email) {
        return ResponseEntity.ok().body(userService.findByEmail(email));
    }


    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok().body(userService.findAll(true));
    }
}