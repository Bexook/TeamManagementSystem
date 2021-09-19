package com.example.TeamManagementSystem.service.model;

import com.example.TeamManagementSystem.domain.dto.UserDTO;
import com.example.TeamManagementSystem.domain.entity.UserEntity;
import lombok.NonNull;

import java.util.List;


public interface UserService {


    UserDTO getUserById(@NonNull Long id);

    boolean registerUser(@NonNull UserEntity userEntity);

    void deleteById(Long id);

    UserDTO findByEmail(String email);

    List<UserDTO> findAll(boolean isActive);
}
