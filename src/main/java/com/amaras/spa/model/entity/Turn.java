package com.amaras.spa.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "turns")
public class Turn implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String hour;

    @Enumerated(EnumType.STRING)
    private EnumStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference //Evita la serialización infinita del usuario dentro de turnos
    private User user;

    public static class TurnBuilder {
        private User user;

        public TurnBuilder user(User user) {
            this.user = user;
            return this;
        }

        public User getUser() {
            return this.user;
        }
    }

}
