package com.amaras.spa.repository;

import com.amaras.spa.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsUserByUsername(String username);

    //Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

}
