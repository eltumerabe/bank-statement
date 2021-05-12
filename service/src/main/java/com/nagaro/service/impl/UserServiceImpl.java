package com.nagaro.service.impl;

import com.nagaro.common.model.dto.UserDto;
import com.nagaro.dataaccess.entity.User;
import com.nagaro.dataaccess.repository.UserRepository;
import com.nagaro.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto getUser(String username, String password) {
        User byUsernameAndAndPassword = userRepository.getByUsernameAndPassword(username, password);
        if (null != byUsernameAndAndPassword) {
            System.out.println("not null");
        }
        return null;
    }
}
