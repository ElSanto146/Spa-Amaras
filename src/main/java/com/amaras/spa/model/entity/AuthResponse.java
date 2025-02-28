package com.amaras.spa.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    //Esta clase nos interesa que devuelva el token de autenticación

    private Long id;
    private String token;
    private String username;
    private String name;
    private String phone;
    private String img; // Si tienes una imagen asociada
    private List<String> roles; // Lista de roles del usuario
}
