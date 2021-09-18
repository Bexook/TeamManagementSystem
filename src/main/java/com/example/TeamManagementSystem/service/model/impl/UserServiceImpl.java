package com.example.TeamManagementSystem.service.model.impl;

import com.example.TeamManagementSystem.changeRequestFeature.annotation.Approver;
import com.example.TeamManagementSystem.changeRequestFeature.annotation.ChangeRequest;
import com.example.TeamManagementSystem.changeRequestFeature.model.enumTypes.OperationType;
import com.example.TeamManagementSystem.model.entity.UserEntity;
import com.example.TeamManagementSystem.model.enumTypes.auth.UserRole;
import com.example.TeamManagementSystem.repository.UserRepository;
import com.example.TeamManagementSystem.service.model.UserService;
import lombok.NonNull;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Approver(userRole = UserRole.ADMIN, repository = UserRepository.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public UserEntity getUserById(@NonNull Long id) {
        return userRepository.getById(id);
    }

    @Override
    @Transactional(Transactional.TxType.MANDATORY)
    public boolean registerUser(@NonNull UserEntity userEntity) {
        userRepository.save(userEntity);
        return Objects.nonNull(userRepository.findByEmail(userEntity.getEmail()));
    }

    @Override
    @ChangeRequest(operationType = OperationType.DELETE)
    public void deleteById(Long id) {
        UserEntity userEntity = userRepository.getById(id);
        userRepository.delete(userEntity);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserEntity> findAll(boolean isActive) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("activeFilter").setParameter("isActive", isActive);
        List<UserEntity> userEntityList = userRepository.findAll();
        session.disableFilter("activeFilter");
        return userEntityList;
    }
}
