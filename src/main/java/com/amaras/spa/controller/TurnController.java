package com.amaras.spa.controller;

import com.amaras.spa.model.dto.TurnDto;
import com.amaras.spa.model.dto.TurnResponseDto;
import com.amaras.spa.service.interfaz.ITurnService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/turn")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class TurnController {

    private final ITurnService turnService;


    @PostMapping("/create")
    public ResponseEntity<TurnResponseDto> createTurn (@RequestBody @Valid TurnDto turnDto){
        TurnResponseDto savedTurn = turnService.createTurn(turnDto);
        URI location = URI.create("/v1/turn/" + savedTurn.getId());
        return ResponseEntity.created(location).body(savedTurn);
    }

    @GetMapping("/turns")
    public ResponseEntity<List<TurnDto>> listTurns(){
        List<TurnDto> turnDtoList = turnService.allTurns();
        if (turnDtoList.isEmpty()){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(turnDtoList);
    }

    @GetMapping("/turn/{date}/{hour}")
    public ResponseEntity<TurnDto> findTurn(@PathVariable LocalDate date, @PathVariable String hour){
        TurnDto turnDto = turnService.findTurnById(date, hour);
        return ResponseEntity.ok(turnDto);
    }

    @GetMapping("/turns/{date}")
    public ResponseEntity<List<TurnDto>> findTurnByDate(@PathVariable LocalDate date){
        List<TurnDto> turnDtoList = turnService.findTurnByDate(date);
        if (turnDtoList.isEmpty()){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(turnDtoList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TurnDto> updateTurn(@PathVariable Long id, @RequestBody TurnDto turnDto){
        return ResponseEntity.ok(turnService.updateTurn(id, turnDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TurnDto> delete (@PathVariable Long id){
        return ResponseEntity.ok(turnService.deleteTurn(id));
    }

}
