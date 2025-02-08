package com.amaras.spa.mapper;

import com.amaras.spa.model.dto.UserDto;
import com.amaras.spa.model.dto.UserDtoUpdate;
import com.amaras.spa.model.dto.UserResponseDto;
import com.amaras.spa.model.dto.UserUpResponseDto;
import com.amaras.spa.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    //Método para mapear UserDto a User
    User toUser(UserDto userDto);


    User toUserUpdate(UserDtoUpdate userDtoUpdate);

    //Método para mapear User a UserDto
    UserDto toUserDto(User user);

    //Método para mapear una lista de User a una lista de UserDto
    List<UserDto> toUserDtoList(List<User> users);

    //Método para actualizar un User con los datos de un UserDto
    void updateUser(@MappingTarget User user, UserDto userDto);

    //Método para actualizar un User con los datos de un UserDtoUpdate
    void updateUserUp(@MappingTarget User user, UserDtoUpdate userDtoUpdate);

    //Método para mapear UserDtoUpdate a User

    UserDtoUpdate toUserDtoUpdate(User user);

    //Método para mapear User a UserResponseDto
    UserResponseDto toUserResponseDto(User user);

    //Método para mapear UserResponseDto a UserDtoUpdate
    UserDtoUpdate toUserDtoUpdateByResponse(UserResponseDto userResponseDto);

    //Método para mapear User a UserUpResponseDto
    UserUpResponseDto toUserUpResponseDto(User user);
}
