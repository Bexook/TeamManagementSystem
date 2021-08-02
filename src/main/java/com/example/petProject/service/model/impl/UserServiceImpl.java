package com.example.petProject.service.model.impl;

import com.example.petProject.model.entity.UserEntity;
import com.example.petProject.repository.UserRepository;
import com.example.petProject.service.model.UserService;
import lombok.NonNull;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

@Service
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
    public boolean registerUser(@NonNull UserEntity userEntity) {
        userRepository.save(userEntity);
        return Objects.nonNull(userRepository.findByEmail(userEntity.getEmail()));
    }

    @Override
    public boolean deleteUser(UserEntity userEntity) {
        userRepository.delete(userEntity);
        return userRepository.getById(userEntity.getId()).isActive();
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
