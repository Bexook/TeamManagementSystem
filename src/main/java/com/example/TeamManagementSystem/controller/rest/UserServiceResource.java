package com.example.TeamManagementSystem.controller.rest;

import com.example.TeamManagementSystem.domain.dto.UserDTO;
import com.example.TeamManagementSystem.service.user.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserServiceResource {


    @Autowired
    private UserService userService;

    @PostMapping("/delete/{id}")
    public void deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteById(id);
    }

    @GetMapping("/get")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam(name = "email") String email) {
        return ResponseEntity.ok().body(userService.findByEmail(email));
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody final UserDTO user) {
        userService.registerUser(user);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok().body(userService.findAll(true));
    }
}