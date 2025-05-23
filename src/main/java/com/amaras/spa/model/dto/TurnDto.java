package com.amaras.spa.model.dto;


import com.amaras.spa.model.entity.EnumStatus;
import com.amaras.spa.model.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TurnDto {

    private Long id;

    @NotNull(message = "La fecha no puede estar vacía")
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    @NotBlank(message = "La hora no puede estar vacía")
    @Column(nullable = false)
    private String hour;

    @Enumerated(EnumType.STRING)
    private EnumStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserResponseDto user;
}
