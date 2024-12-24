package com.amaras.spa.service.interfaz;

import com.amaras.spa.model.dto.UserDto;

import java.util.List;

public interface IUserService {

    List<UserDto> allUsers();

    UserDto findUserById(Long id);

    UserDto updateUser(Long id, UserDto userDto);

    UserDto deleteUser(Long id);

    UserDto saveUser(UserDto userDto);
}
