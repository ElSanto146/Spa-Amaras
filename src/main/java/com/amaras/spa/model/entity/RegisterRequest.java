package com.amaras.spa.model.entity;


import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "El email no puede estar vacío1")
    String username;

    @NotBlank(message = "La contraseña no puede estar vacía1")
    String password;

    @NotBlank(message = "El nombre no puede estar vacío1")
    String name;

    @NotBlank(message = "El teléfono no puede estar vacío1")
    String phone;
}
