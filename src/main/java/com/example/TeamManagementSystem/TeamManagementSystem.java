package com.example.TeamManagementSystem;

import com.tms.common.security.CommonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableWebSecurity
@EnableDiscoveryClient
@ComponentScan({"com.tms.common.*", "com.example.TeamManagementSystem.*"})
@EnableJpaRepositories({"com.tms.common.*", "com.example.TeamManagementSystem.*"})
@EnableAspectJAutoProxy
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        prePostEnabled = true,
        jsr250Enabled = true
)
@EntityScan("com.tms.common.*")
public class TeamManagementSystem {

    public static void main(String[] args) {
        SpringApplication.run(TeamManagementSystem.class, args);


    }

}
