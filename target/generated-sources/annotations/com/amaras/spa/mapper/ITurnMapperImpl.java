package com.amaras.spa.mapper;

import com.amaras.spa.model.dto.TurnDto;
import com.amaras.spa.model.dto.TurnResponseDto;
import com.amaras.spa.model.dto.UserDto;
import com.amaras.spa.model.dto.UserResponseDto;
import com.amaras.spa.model.entity.Turn;
import com.amaras.spa.model.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-08T14:19:14-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class ITurnMapperImpl implements ITurnMapper {

    @Override
    public Turn toTurn(TurnDto turnDto) {
        if ( turnDto == null ) {
            return null;
        }

        Turn.TurnBuilder turn = Turn.builder();

        turn.user( userResponseDtoToUser( turnDto.getUser() ) );
        turn.id( turnDto.getId() );
        turn.date( turnDto.getDate() );
        turn.hour( turnDto.getHour() );
        turn.status( turnDto.getStatus() );

        return turn.build();
    }

    @Override
    public TurnDto toTurnDto(Turn turn) {
        if ( turn == null ) {
            return null;
        }

        TurnDto.TurnDtoBuilder turnDto = TurnDto.builder();

        turnDto.id( turn.getId() );
        turnDto.date( turn.getDate() );
        turnDto.hour( turn.getHour() );
        turnDto.status( turn.getStatus() );
        turnDto.user( userToUserResponseDto( turn.getUser() ) );

        return turnDto.build();
    }

    @Override
    public List<TurnDto> toTurnDtoList(List<Turn> turns) {
        if ( turns == null ) {
            return null;
        }

        List<TurnDto> list = new ArrayList<TurnDto>( turns.size() );
        for ( Turn turn : turns ) {
            list.add( toTurnDto( turn ) );
        }

        return list;
    }

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.name( user.getName() );
        userDto.username( user.getUsername() );
        userDto.phone( user.getPhone() );
        userDto.role( user.getRole() );
        userDto.turns( turnListToTurnResponseDtoList( user.getTurns() ) );

        return userDto.build();
    }

    @Override
    public TurnResponseDto toTurnResponseDto(Turn turn) {
        if ( turn == null ) {
            return null;
        }

        TurnResponseDto.TurnResponseDtoBuilder turnResponseDto = TurnResponseDto.builder();

        turnResponseDto.id( turn.getId() );
        turnResponseDto.date( turn.getDate() );
        turnResponseDto.hour( turn.getHour() );
        turnResponseDto.status( turn.getStatus() );
        turnResponseDto.user( userToUserResponseDto( turn.getUser() ) );

        return turnResponseDto.build();
    }

    @Override
    public UserResponseDto userToUserResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto.UserResponseDtoBuilder userResponseDto = UserResponseDto.builder();

        userResponseDto.id( user.getId() );
        userResponseDto.name( user.getName() );
        userResponseDto.username( user.getUsername() );
        userResponseDto.phone( user.getPhone() );

        return userResponseDto.build();
    }

    protected User userResponseDtoToUser(UserResponseDto userResponseDto) {
        if ( userResponseDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( userResponseDto.getId() );
        user.name( userResponseDto.getName() );
        user.username( userResponseDto.getUsername() );
        user.phone( userResponseDto.getPhone() );

        return user.build();
    }

    protected List<TurnResponseDto> turnListToTurnResponseDtoList(List<Turn> list) {
        if ( list == null ) {
            return null;
        }

        List<TurnResponseDto> list1 = new ArrayList<TurnResponseDto>( list.size() );
        for ( Turn turn : list ) {
            list1.add( toTurnResponseDto( turn ) );
        }

        return list1;
    }
}
