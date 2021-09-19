package com.example.TeamManagementSystem.mapper;

import com.example.TeamManagementSystem.domain.dto.UserDTO;
import com.example.TeamManagementSystem.domain.dto.UserRegisterDTO;
import com.example.TeamManagementSystem.domain.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface UserMapper {

    UserEntity mapUserRegisterDTOToUser(UserRegisterDTO userRegisterDTO);

    UserDTO toUserDTO(UserEntity userEntity);

    UserEntity toUserEntity(UserDTO userDTO);

    List<UserEntity> toUserEntityList(List<UserDTO> userDTO);

    List<UserDTO> toUserDTOList(List<UserEntity> userEntity);

}
