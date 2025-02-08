package com.amaras.spa.service.impl;

import com.amaras.spa.exception.AppException;
import com.amaras.spa.mapper.IUserMapper;
import com.amaras.spa.model.dto.UserDto;
import com.amaras.spa.model.dto.UserDtoUpdate;
import com.amaras.spa.model.dto.UserUpResponseDto;
import com.amaras.spa.model.entity.User;
import com.amaras.spa.repository.UserRepository;
import com.amaras.spa.service.interfaz.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements IUserService {

    private final UserRepository userRepository;
    private final IUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<UserDto> allUsers() {
        return userMapper.toUserDtoList(userRepository.findAll());
    }

    @Override
    public UserDto findUserById(Long id) {
        return userMapper.toUserDto(userRepository.findById(id).orElseThrow(
                () -> new AppException("El usuario con id:["+id+"] no fue encontrado", HttpStatus.NOT_FOUND)
        ));
    }

    @Override
    public UserUpResponseDto updateUser(Long id, UserDtoUpdate userDtoUpdate) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new AppException("El usuario con id: [" + id + "] no fue encontrado", HttpStatus.NOT_FOUND)
        );


        if (userDtoUpdate.getName() != null && !userDtoUpdate.getName().isBlank()) {
                user.setName(userDtoUpdate.getName());
        }
        if (userDtoUpdate.getUsername() != null && !userDtoUpdate.getUsername().isBlank()) {
            if (userRepository.existsUserByUsername(userDtoUpdate.getUsername())){
                throw new AppException("El Email: " +userDtoUpdate.getUsername()+  " ya está en uso", HttpStatus.CONFLICT);
            } else {
                user.setUsername(userDtoUpdate.getUsername());
            }
        }
        if (userDtoUpdate.getPassword() != null && !userDtoUpdate.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(userDtoUpdate.getPassword()));
        }
        if (userDtoUpdate.getPhone() != null && !userDtoUpdate.getPhone().isBlank()) {
            user.setPhone(userDtoUpdate.getPhone());
        }
        if (userDtoUpdate.getRole() != null && !userDtoUpdate.getRole().toString().isBlank()) {
            user.setRole(userDtoUpdate.getRole());
        }

        userRepository.save(user);
        return userMapper.toUserUpResponseDto(user);
    }

    @Override
    public UserDto deleteUser(Long id) {
        UserDto userDto = findUserById(id);
        userRepository.deleteById(id);
        return userDto;
    }
}
