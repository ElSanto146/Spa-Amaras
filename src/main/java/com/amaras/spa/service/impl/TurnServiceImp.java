package com.amaras.spa.service.impl;

import com.amaras.spa.exception.AppException;
import com.amaras.spa.mapper.ITurnMapper;
import com.amaras.spa.model.dto.TurnDto;
import com.amaras.spa.model.dto.TurnResponseDto;
import com.amaras.spa.model.entity.Turn;
import com.amaras.spa.repository.TurnRepository;
import com.amaras.spa.service.interfaz.ITurnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TurnServiceImp implements ITurnService {

    private final TurnRepository turnRepository;
    private final ITurnMapper turnMapper;

    @Override
    public List<TurnDto> allTurns() {
        List<TurnDto> turnListDto = turnMapper.toTurnDtoList(turnRepository.findAll());
        return turnListDto;
    }

    @Override
    public TurnResponseDto createTurn(TurnDto turnDto) {
        existTurn(turnDto.getDate(), turnDto.getHour());
        Turn turn = turnRepository.save(turnMapper.toTurn(turnDto));

        return turnMapper.toTurnResponseDto(turn);
    }

    @Override
    public TurnDto updateTurn(Long id, TurnDto turnDto) {
        Turn turn = turnRepository.findById(id).orElseThrow(() ->
                new AppException("El turno con id:["+id+"] no fue encontrado", HttpStatus.NOT_FOUND));

        if (turnDto.getDate() != null && !turnDto.getDate().toString().isBlank()){
            //existTurn(turnDto.getDate(), turnDto.getHour());
            turn.setDate(turnDto.getDate());
        }
        if (turnDto.getHour() != null && !turnDto.getHour().isBlank()){
            //existTurn(turnDto.getDate(), turnDto.getHour());
            turn.setHour(turnDto.getHour());
        }
        if (turnDto.getStatus() != null && !turnDto.getStatus().toString().isBlank()){
            turn.setStatus(turnDto.getStatus());
        }

        turnRepository.save(turn);

        return turnMapper.toTurnDto(turn);
    }

    @Override
    public TurnDto deleteTurn(Long id) {
        TurnDto turnDto = turnMapper.toTurnDto(turnRepository.findById(id).orElseThrow(
                () -> new AppException("El turno con id:["+id+"] no fue encontrado", HttpStatus.NOT_FOUND)
        ));
        turnRepository.deleteById(id);
        return turnDto;
    }

    @Override
    public TurnDto findTurnById(LocalDate date, String hour) {
        TurnDto turnDto = turnMapper.toTurnDto(turnRepository.findTurnByDateAndHour(date, hour).orElseThrow(
                () -> new AppException("No está asignado el turno con fecha:["+date+"] y hora:["+hour+"]", HttpStatus.NOT_FOUND)
        ));

        return turnDto;
    }

    @Override
    public List<TurnDto> findTurnByDate(LocalDate date) {
       List<TurnDto> turnDtoList = turnMapper.toTurnDtoList(turnRepository.findTurnByDate(date));
        return turnDtoList;
    }


    private void existTurn(LocalDate date, String hour){
        if (turnRepository.existsTurnByDateAndHour(date, hour)){
            throw new AppException("Ya hay un turno asignado en esa fecha y horario", HttpStatus.CONFLICT);
        }
    }

}
