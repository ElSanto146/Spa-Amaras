package com.amaras.spa.mapper;

import com.amaras.spa.model.dto.TurnResponseDto;
import com.amaras.spa.model.dto.UserDto;
import com.amaras.spa.model.dto.UserDtoUpdate;
import com.amaras.spa.model.dto.UserResponseDto;
import com.amaras.spa.model.dto.UserUpResponseDto;
import com.amaras.spa.model.entity.Turn;
import com.amaras.spa.model.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-01T14:15:37-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public User toUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( userDto.getId() );
        user.name( userDto.getName() );
        user.username( userDto.getUsername() );
        user.phone( userDto.getPhone() );
        user.role( userDto.getRole() );
        user.turns( turnResponseDtoListToTurnList( userDto.getTurns() ) );

        return user.build();
    }

    @Override
    public User toUserUpdate(UserDtoUpdate userDtoUpdate) {
        if ( userDtoUpdate == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( userDtoUpdate.getId() );
        user.name( userDtoUpdate.getName() );
        user.username( userDtoUpdate.getUsername() );
        user.password( userDtoUpdate.getPassword() );
        user.phone( userDtoUpdate.getPhone() );
        user.role( userDtoUpdate.getRole() );
        user.turns( turnResponseDtoListToTurnList( userDtoUpdate.getTurns() ) );

        return user.build();
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
    public List<UserDto> toUserDtoList(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( users.size() );
        for ( User user : users ) {
            list.add( toUserDto( user ) );
        }

        return list;
    }

    @Override
    public void updateUser(User user, UserDto userDto) {
        if ( userDto == null ) {
            return;
        }

        user.setId( userDto.getId() );
        user.setName( userDto.getName() );
        user.setUsername( userDto.getUsername() );
        user.setPhone( userDto.getPhone() );
        user.setRole( userDto.getRole() );
        if ( user.getTurns() != null ) {
            List<Turn> list = turnResponseDtoListToTurnList( userDto.getTurns() );
            if ( list != null ) {
                user.getTurns().clear();
                user.getTurns().addAll( list );
            }
            else {
                user.setTurns( null );
            }
        }
        else {
            List<Turn> list = turnResponseDtoListToTurnList( userDto.getTurns() );
            if ( list != null ) {
                user.setTurns( list );
            }
        }
    }

    @Override
    public void updateUserUp(User user, UserDtoUpdate userDtoUpdate) {
        if ( userDtoUpdate == null ) {
            return;
        }

        user.setId( userDtoUpdate.getId() );
        user.setName( userDtoUpdate.getName() );
        user.setUsername( userDtoUpdate.getUsername() );
        user.setPassword( userDtoUpdate.getPassword() );
        user.setPhone( userDtoUpdate.getPhone() );
        user.setRole( userDtoUpdate.getRole() );
        if ( user.getTurns() != null ) {
            List<Turn> list = turnResponseDtoListToTurnList( userDtoUpdate.getTurns() );
            if ( list != null ) {
                user.getTurns().clear();
                user.getTurns().addAll( list );
            }
            else {
                user.setTurns( null );
            }
        }
        else {
            List<Turn> list = turnResponseDtoListToTurnList( userDtoUpdate.getTurns() );
            if ( list != null ) {
                user.setTurns( list );
            }
        }
    }

    @Override
    public UserDtoUpdate toUserDtoUpdate(User user) {
        if ( user == null ) {
            return null;
        }

        UserDtoUpdate.UserDtoUpdateBuilder userDtoUpdate = UserDtoUpdate.builder();

        userDtoUpdate.id( user.getId() );
        userDtoUpdate.name( user.getName() );
        userDtoUpdate.username( user.getUsername() );
        userDtoUpdate.password( user.getPassword() );
        userDtoUpdate.phone( user.getPhone() );
        userDtoUpdate.role( user.getRole() );
        userDtoUpdate.turns( turnListToTurnResponseDtoList( user.getTurns() ) );

        return userDtoUpdate.build();
    }

    @Override
    public UserResponseDto toUserResponseDto(User user) {
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

    @Override
    public UserDtoUpdate toUserDtoUpdateByResponse(UserResponseDto userResponseDto) {
        if ( userResponseDto == null ) {
            return null;
        }

        UserDtoUpdate.UserDtoUpdateBuilder userDtoUpdate = UserDtoUpdate.builder();

        userDtoUpdate.id( userResponseDto.getId() );
        userDtoUpdate.name( userResponseDto.getName() );
        userDtoUpdate.username( userResponseDto.getUsername() );
        userDtoUpdate.phone( userResponseDto.getPhone() );

        return userDtoUpdate.build();
    }

    @Override
    public UserUpResponseDto toUserUpResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserUpResponseDto.UserUpResponseDtoBuilder userUpResponseDto = UserUpResponseDto.builder();

        userUpResponseDto.id( user.getId() );
        userUpResponseDto.name( user.getName() );
        userUpResponseDto.username( user.getUsername() );
        userUpResponseDto.phone( user.getPhone() );
        userUpResponseDto.role( user.getRole() );
        userUpResponseDto.turns( turnListToTurnResponseDtoList( user.getTurns() ) );

        return userUpResponseDto.build();
    }

    protected Turn turnResponseDtoToTurn(TurnResponseDto turnResponseDto) {
        if ( turnResponseDto == null ) {
            return null;
        }

        Turn.TurnBuilder turn = Turn.builder();

        if ( turnResponseDto.getUser() != null ) {
            if ( turn.getUser() == null ) {
                turn.user( User.builder().build() );
            }
            updateUserUp( turn.getUser(), toUserDtoUpdateByResponse( turnResponseDto.getUser() ) );
        }
        turn.id( turnResponseDto.getId() );
        turn.date( turnResponseDto.getDate() );
        turn.hour( turnResponseDto.getHour() );
        turn.status( turnResponseDto.getStatus() );

        return turn.build();
    }

    protected List<Turn> turnResponseDtoListToTurnList(List<TurnResponseDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Turn> list1 = new ArrayList<Turn>( list.size() );
        for ( TurnResponseDto turnResponseDto : list ) {
            list1.add( turnResponseDtoToTurn( turnResponseDto ) );
        }

        return list1;
    }

    protected TurnResponseDto turnToTurnResponseDto(Turn turn) {
        if ( turn == null ) {
            return null;
        }

        TurnResponseDto.TurnResponseDtoBuilder turnResponseDto = TurnResponseDto.builder();

        turnResponseDto.id( turn.getId() );
        turnResponseDto.date( turn.getDate() );
        turnResponseDto.hour( turn.getHour() );
        turnResponseDto.status( turn.getStatus() );
        turnResponseDto.user( toUserResponseDto( turn.getUser() ) );

        return turnResponseDto.build();
    }

    protected List<TurnResponseDto> turnListToTurnResponseDtoList(List<Turn> list) {
        if ( list == null ) {
            return null;
        }

        List<TurnResponseDto> list1 = new ArrayList<TurnResponseDto>( list.size() );
        for ( Turn turn : list ) {
            list1.add( turnToTurnResponseDto( turn ) );
        }

        return list1;
    }
}
