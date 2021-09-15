package com.example.petProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@EnableAspectJAutoProxy
@SpringBootApplication
public class TeamManagementSystem {

    public static void main(String[] args) {
        SpringApplication.run(TeamManagementSystem.class, args);


    }

}
