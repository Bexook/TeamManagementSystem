package com.example.TeamManagementSystem.configuration;

import com.example.TeamManagementSystem.changeRequestFeature.configs.ChangeRequestRepositoriesConfiguration;
import com.example.TeamManagementSystem.changeRequestFeature.configs.Sources;
import com.example.TeamManagementSystem.model.entity.UserEntity;
import com.example.TeamManagementSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Configuration
public class ChangeRequestConfigs implements ChangeRequestRepositoriesConfiguration<Long> {


    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;
    Map<String, Supplier<?>> userRepositoryOperation;

    @Bean
    @Override
    public Sources<Long> loadRepositories() {
        Sources<Long> sources = new Sources<>(entityManager);
        sources.addOperation(List.class, userRepositoryOperation);
        sources.addRepository(UserEntity.class, userRepository);
        return sources;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        userRepositoryOperation = new HashMap<>();
        userRepositoryOperation.put("findAll(UserEntity)", userRepository::findAll);
    }
}
