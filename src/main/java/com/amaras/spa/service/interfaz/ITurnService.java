package com.amaras.spa.service.interfaz;

import com.amaras.spa.model.dto.TurnDto;
import com.amaras.spa.model.dto.TurnResponseDto;
import com.amaras.spa.model.dto.UserDto;

import java.time.LocalDate;
import java.util.List;

public interface ITurnService {

    List<TurnDto> allTurns();

    TurnResponseDto createTurn(TurnDto turnDto);

    TurnDto updateTurn(Long id, TurnDto turnDto);

    TurnDto deleteTurn(Long id);

    TurnDto findTurnById(LocalDate date, String hour);

    List<TurnDto> findTurnByDate(LocalDate date);

}
