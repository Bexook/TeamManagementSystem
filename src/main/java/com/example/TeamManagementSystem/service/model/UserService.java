package com.example.TeamManagementSystem.service.model;

import com.tms.dao.tmsdao.domain.UserEntity;
import com.tms.dao.tmsdao.domain.dto.UserDTO;
import lombok.NonNull;

import java.util.List;


public interface UserService {


    UserDTO getUserById(@NonNull Long id);

    UserEntity registerUser(@NonNull UserEntity userEntity);

    void deleteById(Long id);

    UserDTO findByEmail(String email);

    List<UserDTO> findAll(boolean isActive);

    void update(UserDTO userDTO);
}
