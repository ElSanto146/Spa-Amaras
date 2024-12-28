package com.amaras.spa.service.impl;

import com.amaras.spa.exception.AppException;
import com.amaras.spa.mapper.IUserMapper;
import com.amaras.spa.model.dto.UserDto;
import com.amaras.spa.model.entity.User;
import com.amaras.spa.repository.UserRepository;
import com.amaras.spa.service.interfaz.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements IUserService {

    private final UserRepository userRepository;
    private final IUserMapper userMapper;


    @Override
    public List<UserDto> allUsers() {
       List<UserDto> userDtoList = userMapper.toUserDtoList(userRepository.findAll());
        return userDtoList;
    }

    @Override
    public UserDto findUserById(Long id) {
        UserDto userDto = userMapper.toUserDto(userRepository.findById(id).orElseThrow(
                () -> new AppException("El usuario con id:["+id+"] no fue encontrado", HttpStatus.NOT_FOUND)
        ));
        return userDto;
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userMapper.toUser(findUserById(id));
        userRepository.save(user);
        return userMapper.toUserDto(userMapper.updateUser(user, userDto));
    }

    @Override
    public UserDto deleteUser(Long id) {
        UserDto userDto = findUserById(id);
        userRepository.deleteById(id);
        return userDto;

       /* User user = userMapper.toUser(findUserById(id));
        userRepository.deleteById(user.getId());
        return userMapper.toUserDto(user);*/
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        existUser(userDto.getUsername());
        User user = userRepository.save(userMapper.toUser(userDto));
        return userMapper.toUserDto(user);
    }

    private void existUser(String username) {
        if (userRepository.existsUserByUsername(username)){
            throw new AppException("El email '"+username+"' ya está en uso", HttpStatus.CONFLICT);
        }
    }
}
