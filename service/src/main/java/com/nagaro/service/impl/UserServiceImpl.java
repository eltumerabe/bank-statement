package com.nagaro.service.impl;

import com.nagaro.common.exception.EntityNotFoundException;
import com.nagaro.common.model.dto.UserDto;
import com.nagaro.common.model.entity.User;
import com.nagaro.dataaccess.repository.UserRepository;
import com.nagaro.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto getUser(String username, String password) {
        String encode = bCryptPasswordEncoder.encode(password);
        User byUsernameAndAndPassword = userRepository.getByUsernameAndPassword(username, encode);
        if (null != byUsernameAndAndPassword) {
            return modelMapper.map(byUsernameAndAndPassword, UserDto.class);
        }
        return null;
    }

    @Override
    public UserDto getUser(String username) {
        User byUsername = userRepository.getByUsername(username);
        if (null != byUsername) {
            return modelMapper.map(byUsername, UserDto.class);
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = userRepository.getByUsername(username);
        if (byUsername == null)
            //throw new UsernameNotFoundException(username);
            throw new EntityNotFoundException(String.class, "username", username);
        return new org.springframework.security.core.userdetails.User(byUsername.getUsername(), byUsername.getPassword(),
                true,
                true, true,
                true, new ArrayList<>());
    }
}
