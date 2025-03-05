package com.amaras.spa.service.impl;


import com.amaras.spa.model.entity.*;
import com.amaras.spa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    //Inyectar dependencias
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;//El login tiene que autenticarse

    public AuthResponse login(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (request.getUsername(), request.getPassword()));
        //Una vez autenticado se genera el token
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow(
                ()-> new UsernameNotFoundException("Usuario no encontrado"));
        //Generar el token JWT
        String token = jwtService.getToken(user);

        //Obtener los roles como lista de strings
        List<String> roles = user.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .collect(Collectors.toList());

        //Construir y devolver la respuesta
        return AuthResponse.builder()
                .token(token)
                .name(userRepository.findByUsername(request.getUsername()).get().getName()) // Nombre del usuario
                .username(userRepository.findByUsername(request.getUsername()).get().getUsername()) // Email del usuario
                .phone(userRepository.findByUsername(request.getUsername()).get().getPhone())// Teléfono del usuario
                .id(userRepository.findByUsername(request.getUsername()).get().getId())// Id del usuario
                .img("") // Imagen del usuario
                .roles(roles) // Roles del usuario
                .build();
    }

    public AuthResponse register(RegisterRequest request){

        if (userRepository.existsUserByUsername(request.getUsername())){
            throw new RuntimeException("El Email: " +request.getUsername()+  " ya está en uso");
        }
        User user = User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return AuthResponse.builder().
                token(jwtService.getToken(user))
                .id(user.getId())
                .name(user.getName())                // Nombre del usuario
                .username(user.getUsername())        // Username
                .phone(user.getPhone())              // Teléfono
                .roles(Collections.singletonList(user.getRole().toString()))// Rol en formato String
                .build();
    }
}
