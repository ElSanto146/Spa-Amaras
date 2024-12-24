package com.amaras.spa.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import java.util.Date;


@Builder
@Entity
@Table(name = "turns")
public class Turn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private Date hour;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Turn() {
    }

    public Turn(Long id, Date date, Date hour, String status, User user) {
        this.id = id;
        this.date = date;
        this.hour = hour;
        this.status = status;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getHour() {
        return hour;
    }

    public void setHour(Date hour) {
        this.hour = hour;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
