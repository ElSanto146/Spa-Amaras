package com.amaras.spa.service.Impl;

import com.amaras.spa.model.dto.UserDto;
import com.amaras.spa.repository.UserRepository;
import com.amaras.spa.service.Interface.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDto> allUsers() {
        return List.of();
    }

    @Override
    public UserDto findUserById(Long id) {
        return null;
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        return null;
    }

    @Override
    public UserDto deleteUser(Long id) {
        return null;
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto findUserByEmail(String email) {
        return null;
    }
}
