package com.example.petProject.mapper;

import com.example.petProject.model.dto.UserRegisterDTO;
import com.example.petProject.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;

@Mapper
public interface UserMapper {

    @Mappings({
            @Mapping(source = "username", target = "username", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS),
            @Mapping(source = "password", target = "password", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS),
            @Mapping(source = "email", target = "email", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS),
    })
    UserEntity mapUserRegisterDTOToUser(UserRegisterDTO userRegisterDTO);

}
