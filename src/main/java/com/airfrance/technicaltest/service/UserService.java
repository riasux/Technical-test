package com.airfrance.technicaltest.service;

import com.airfrance.technicaltest.dto.user.UserDto;

public interface UserService {

    UserDto findById(long userId);

    UserDto save(UserDto userDto);
}
