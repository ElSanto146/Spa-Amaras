package com.amaras.spa.repository;

import com.amaras.spa.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsUserByEmail(String email);

}
