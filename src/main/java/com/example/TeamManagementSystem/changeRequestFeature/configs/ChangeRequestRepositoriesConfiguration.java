package com.example.TeamManagementSystem.changeRequestFeature.configs;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public interface ChangeRequestRepositoriesConfiguration<T extends Number> extends InitializingBean {

    @Bean
    Sources<T> loadRepositories();

}
