package com.amaras.spa.model.dto;

import com.amaras.spa.model.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @Email(message = "El email no es válido")
    private String username;

    @NotBlank(message = "El teléfono no puede estar vacío")
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    private List<TurnResponseDto> turns;

}
