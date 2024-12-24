package com.amaras.spa.controller;

import com.amaras.spa.model.dto.UserDto;
import com.amaras.spa.service.interfaz.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService){
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<UserDto> createUser (@RequestBody @Valid UserDto userDto){
        UserDto savedUser = userService.saveUser(userDto);
        URI location = URI.create("/v1/user/" + savedUser.getId());
        return ResponseEntity.created(location).body(savedUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> ListUsers(){
        List<UserDto> userDtoList = userService.allUsers();
        if (userDtoList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(userDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody @Valid UserDto userDto){
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }

}
