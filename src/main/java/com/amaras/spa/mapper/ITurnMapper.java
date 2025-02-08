package com.amaras.spa.mapper;


import com.amaras.spa.model.dto.TurnDto;
import com.amaras.spa.model.dto.TurnResponseDto;
import com.amaras.spa.model.dto.UserDto;
import com.amaras.spa.model.dto.UserResponseDto;
import com.amaras.spa.model.entity.Turn;
import com.amaras.spa.model.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITurnMapper {

    //Método para mapear TurnDto a Turn
    Turn toTurn(TurnDto turnDto);

    //Método para mapear Turn a TurnDto
    TurnDto toTurnDto(Turn turn);

    //Método para mapear una lista de Turn a una lista de TurnDto
    List<TurnDto> toTurnDtoList(List<Turn> turns);

    UserDto toUserDto(User user);

    //Método para mapear un Turn a TurnResponseDto
    TurnResponseDto toTurnResponseDto(Turn turn);

    UserResponseDto userToUserResponseDto(User user);

}
