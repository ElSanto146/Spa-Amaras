package com.amaras.spa.repository;

import com.amaras.spa.model.entity.Turn;
import com.amaras.spa.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TurnRepository extends JpaRepository<Turn, Long> {

    List<Turn> findTurnByDate(LocalDate date);

    List<Turn> findTurnByUser(User user);

    Boolean existsTurnByDateAndHour (LocalDate date, String hour);

    Optional<Turn> findTurnByDateAndHour(LocalDate date, String hour);

}
