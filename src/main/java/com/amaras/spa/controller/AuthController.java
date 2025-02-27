package com.amaras.spa.controller;

import com.amaras.spa.model.entity.AuthResponse;
import com.amaras.spa.model.entity.LoginRequest;
import com.amaras.spa.model.entity.RegisterRequest;
import com.amaras.spa.service.impl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    //Inyectamos la depencia del servicio
    private final AuthService authService;

    //Métodos sencillos para pruebas endpoint públicos
    @PostMapping(value = "login")
    //Accedemos a las credenciasles del usuario
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

}
