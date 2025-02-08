package com.amaras.spa.service.interfaz;

import com.amaras.spa.model.dto.UserDto;
import com.amaras.spa.model.dto.UserDtoUpdate;
import com.amaras.spa.model.dto.UserUpResponseDto;

import java.util.List;

public interface IUserService {

    List<UserDto> allUsers();

    UserDto findUserById(Long id);

    UserUpResponseDto updateUser(Long id, UserDtoUpdate userDtoUpdate);

    UserDto deleteUser(Long id);
}
