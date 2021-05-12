package com.nagaro.service;

import com.nagaro.common.model.dto.UserDto;

public interface UserService {
    UserDto getUser(String username, String password);
}
