package com.airfrance.technicaltest.mapping;

import com.airfrance.technicaltest.dto.user.UserDto;
import com.airfrance.technicaltest.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity User and its DTO called UserDTO.
 *
 * @author r-fonkoue
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserMapper {

    UserDto userEntityToUserDTO(UserEntity userEntity);

    @Mapping(target = "username", expression = "java(userDto.getUsername().trim())")
    @Mapping(target = "country", expression = "java(userDto.getCountry().trim())")
    UserEntity userDTOToUserEntity(UserDto userDto);

}
