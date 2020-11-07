package com.cardIndex.services.mappers;

import com.cardIndex.domain.dto.UserDto;
import com.cardIndex.domain.entity.User;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "name", ignore = true)
    UserDto userToUserDto(User user);
    List<UserDto> usersToUserDtos(List<User> users);

//    @Mapping(target = "dates", ignore = true)
//    @Mapping(target = "id", source = "userId")
//    User userDtoToUser(UserDto userDto);


//    List<User> userDtosToUsers(List<UserDto> userDtos);

}
