package com.example.TeamManagementSystem.configuration;

import com.example.TeamManagementSystem.changeRequestFeature.configs.ChangeRequestRepositoriesConfiguration;
import com.example.TeamManagementSystem.changeRequestFeature.configs.Sources;
import com.example.TeamManagementSystem.repository.TeamMemberRepository;
import com.tms.common.domain.TeamMemberEntity;
import com.tms.common.domain.UserEntity;
import com.tms.common.domain.enumTypes.auth.UserRole;
import com.tms.common.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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
    @Autowired
    private TeamMemberRepository teamMemberRepository;
    private Map<String, Supplier<?>> userRepositoryOperation;
    private Map<String, Supplier<?>> teamMemberRepoOperation;

    @Bean
    @Primary
    @Override
    public Sources<Long> loadRepositories() {
        Sources<Long> sources = new Sources<>(entityManager);
        sources.addOperation(List.class, userRepositoryOperation);
        sources.addRepository(UserEntity.class, userRepository);
        sources.addRepository(TeamMemberEntity.class, teamMemberRepository);
        sources.setApproverRole(UserRole.ADMIN.toString());
        return sources;
    }


    @Override
    public void afterPropertiesSet() {
        userRepositoryOperation = new HashMap<>();
        userRepositoryOperation.put("findAll(UserEntity)", userRepository::findAll);

        teamMemberRepoOperation = new HashMap<>();
        teamMemberRepoOperation.put("findAll(TeamMemberEntity)", teamMemberRepository::findAll);
    }
}
