package com.example.TeamManagementSystem.service.user;

import com.example.TeamManagementSystem.domain.dto.UserDTO;
import com.example.TeamManagementSystem.domain.entity.UserEntity;
import lombok.NonNull;

import java.util.List;


public interface UserService {


    UserDTO getUserById(@NonNull Long id);

    UserEntity registerUser(@NonNull UserDTO userEntity);

    void deleteById(Long id);

    UserDTO findByEmail(String email);

    List<UserDTO> findAll(boolean isActive);

    void update(UserDTO userDTO);
}
