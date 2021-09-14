package com.airfrance.technicaltest.mapping;

import com.airfrance.technicaltest.dto.user.UserDto;
import com.airfrance.technicaltest.entity.UserEntity;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity User and its DTO called UserDTO.
 *
 * @author r-fonkoue
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserMapper {

    UserDto userEntityToUserDTO(UserEntity userEntity);

    UserEntity userDTOToUserEntity(UserDto userDto);

}
