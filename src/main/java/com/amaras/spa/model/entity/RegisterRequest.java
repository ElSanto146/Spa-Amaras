package com.amaras.spa.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    //Esta clase es un POJO que representa la petición de registro

    String username;
    String password;
    String name;
    String lastName;
}
