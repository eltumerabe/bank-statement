package com.nagaro.web;

import com.nagaro.common.model.dto.UserDto;
import com.nagaro.common.model.ui.UserRest;
import com.nagaro.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/auth", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserRest serviceName(@RequestBody UserDto userDto) {
        return modelMapper.map(userService.getUser(userDto.getUsername(), userDto.getPassword()), UserRest.class);
    }
}
