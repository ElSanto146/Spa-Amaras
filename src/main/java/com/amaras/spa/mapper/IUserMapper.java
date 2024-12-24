package com.amaras.spa.mapper;

import com.amaras.spa.model.dto.UserDto;
import com.amaras.spa.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    //Método para mapear UserDto a User
    User toUser(UserDto userDto);

    //Método para mapear User a UserDto
    UserDto toUserDto(User user);

    //Método para mapear una lista de User a una lista de UserDto
    List<UserDto> toUserDtoList(List<User> users);

    //Método para actualizar un User con los datos de un UserDto
    User updateUser(@MappingTarget User user, UserDto userDto);
}
