package com.amaras.spa.controller;

import com.amaras.spa.model.dto.UserDto;
import com.amaras.spa.model.dto.UserDtoUpdate;
import com.amaras.spa.model.dto.UserUpResponseDto;
import com.amaras.spa.service.interfaz.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin(origins = "https://amaras-site.netlify.app")
public class UserController {

    private final IUserService userService;

    @GetMapping("/admin/users")
    public ResponseEntity<List<UserDto>> ListUsers(){
        List<UserDto> userDtoList = userService.allUsers();
        if (userDtoList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(userDtoList);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> findUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserUpResponseDto> updateUser(@PathVariable Long id, @RequestBody UserDtoUpdate userDtoUpdate){
        return ResponseEntity.ok(userService.updateUser(id, userDtoUpdate));
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }

}
