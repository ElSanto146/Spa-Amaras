package com.amaras.spa.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "turns")
public class Turn implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime hour;

    @Enumerated(EnumType.STRING)
    private EnumStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
