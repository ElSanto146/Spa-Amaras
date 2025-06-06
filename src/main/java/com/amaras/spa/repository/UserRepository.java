package com.amaras.spa.repository;


import com.amaras.spa.model.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsUserByUsername(String username);

    Optional<User> findByUsername(String username);

    @EntityGraph (attributePaths = {"turns"})
    Optional<User> findById(Long id);

}
