package com.raport.services.mappers;

import com.raport.domain.entity.UserEntity;
import com.raport.domain.model.UserDto;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "name", ignore = true)
    UserDto userToUserDto(UserEntity userEntity);
    List<UserDto> usersToUserDtos(List<UserEntity> userEntities);

//    @Mapping(target = "dates", ignore = true)
//    @Mapping(target = "id", source = "userId")
//    UserEntity userDtoToUser(UserDto userDto);


//    List<UserEntity> userDtosToUsers(List<UserDto> userDtos);

}
