package com.example.petProject.controller.rest;

import com.example.petProject.service.model.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetRestController {


    @Autowired
    private UserService userService;


    @PostMapping("/del/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }

}