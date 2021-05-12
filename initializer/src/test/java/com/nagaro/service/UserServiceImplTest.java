package com.nagaro.service;

import com.nagaro.common.model.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Mock
    com.nagaro.service.impl.UserServiceImpl userService;

    UserDto userDto;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userDto = new UserDto();
        userDto.setUsername("user");
        userDto.setPassword("user");
    }

    @Test
    final void testGetUserWithUserNameAndPassword() {
        when(userService.getUser(userDto.getUsername(), userDto.getPassword())).thenReturn(userDto);
        assertNotNull(userDto);
    }

    final void testGetUserWithUserName() {
        when(userService.getUser(userDto.getUsername())).thenReturn(userDto);
        assertNotNull(userDto);
    }
}
