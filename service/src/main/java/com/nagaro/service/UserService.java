package com.nagaro.service;

import com.nagaro.common.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto getUser(String username, String password);
    UserDto getUser(String username);
}
