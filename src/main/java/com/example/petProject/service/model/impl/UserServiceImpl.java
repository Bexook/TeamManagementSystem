package com.example.petProject.service.model.impl;

import com.example.petProject.changeRequestFeature.annotation.Approver;
import com.example.petProject.changeRequestFeature.annotation.ChangeRequest;
import com.example.petProject.model.entity.UserEntity;
import com.example.petProject.model.enumTypes.auth.UserRole;
import com.example.petProject.repository.TeamMemberRepository;
import com.example.petProject.repository.UserRepository;
import com.example.petProject.service.model.UserService;
import lombok.NonNull;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Approver(id = 1, userRole = UserRole.ADMIN, repository = TeamMemberRepository.class)
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
    @Transactional
    public boolean registerUser(@NonNull UserEntity userEntity) {
        userRepository.save(userEntity);
        return Objects.nonNull(userRepository.findByEmail(userEntity.getEmail()));
    }

    @Override
    @ChangeRequest
    @Transactional
    public void deleteById(Long id) {
        UserEntity userEntity = userRepository.getById(id);
        userRepository.delete(userEntity);
        userRepository.getById(userEntity.getId());
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
