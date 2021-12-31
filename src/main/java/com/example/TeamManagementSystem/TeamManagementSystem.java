package com.example.TeamManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableAspectJAutoProxy
@SpringBootApplication
@EntityScan("com.tms.dao.tmsdao.*")
public class TeamManagementSystem {

    public static void main(String[] args) {
        SpringApplication.run(TeamManagementSystem.class, args);


    }

}
