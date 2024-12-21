package com.amaras.spa.repository;

import com.amaras.spa.model.entity.Turn;
import com.amaras.spa.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TurnRepository extends JpaRepository<Turn, Long> {

    List<Turn> findTurnByDate(Date date);

    List<Turn> findTurnByUser(User user);


}
