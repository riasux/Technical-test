package com.airfrance.technicaltest.service;

import com.airfrance.technicaltest.dao.UserRepository;
import com.airfrance.technicaltest.dto.user.UserDto;
import com.airfrance.technicaltest.entity.UserEntity;
import com.airfrance.technicaltest.mapping.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


/**
 * Implementation of create and get user service
 *
 * @author r-fonkoue
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final MessageSource messageSource;

    @Override
    public UserDto findById(long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(messageSource.getMessage("error.user.not.found", null, LocaleContextHolder.getLocale()), userId)));

        return userMapper.userEntityToUserDTO(userEntity);
    }

    @Override
    public UserDto save(UserDto userDto) {
        UserEntity userEntity = userMapper.userDTOToUserEntity(userDto);
        return userMapper.userEntityToUserDTO(userRepository.save(userEntity));
    }
}
