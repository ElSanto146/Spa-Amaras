package com.amaras.spa.controller;

import com.amaras.spa.model.dto.UserDto;
import com.amaras.spa.service.interfaz.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService){
        this.userService = userService;
    }

    @PostMapping("/admin/create")
    public ResponseEntity<UserDto> createUser (@RequestBody @Valid UserDto userDto){
        UserDto savedUser = userService.saveUser(userDto);
        URI location = URI.create("/v1/user/" + savedUser.getId());
        return ResponseEntity.created(location).body(savedUser);
    }

    @GetMapping("/user/users")
    public ResponseEntity<List<UserDto>> ListUsers(){
        List<UserDto> userDtoList = userService.allUsers();
        if (userDtoList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(userDtoList);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin")
    public ResponseEntity<String> demoadmin(){
        return ResponseEntity.ok("Desde endpoint protegido para admin");
    }

    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/user")
    public ResponseEntity<String> demouser(){
        return ResponseEntity.ok("Desde endpoint protegido para user");
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
