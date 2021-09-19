package com.example.TeamManagementSystem.controller.rest;

import com.example.TeamManagementSystem.domain.dto.UserDTO;
import com.example.TeamManagementSystem.service.model.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserServiceResource {


    @Autowired
    private UserService userService;

    @PostMapping("/delete/{id}")
    public void deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteById(id);
    }

    @GetMapping("/")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestPart(name = "email") String email) {
        return ResponseEntity.ok().body(userService.findByEmail(email));
    }


    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok().body(userService.findAll(true));
    }
}