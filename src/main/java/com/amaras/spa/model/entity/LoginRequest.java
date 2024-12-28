package com.amaras.spa.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    //Esta clase es un POJO que representa la petición de login
    private String username;
    private String password;
}
