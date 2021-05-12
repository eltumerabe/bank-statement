package com.nagaro.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagaro.common.model.dto.UserDto;
import com.nagaro.common.utils.SpringUtils;
import com.nagaro.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    private String contentType;


    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            contentType = req.getHeader("Accept");
            UserDto creds = new ObjectMapper()
                    .readValue(req.getInputStream(), UserDto.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String userName = ((User) auth.getPrincipal()).getUsername();
        Environment environment = (Environment) SpringUtils.getBean("environment");
        final Date expirationDate = new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("session.expiration")));
        String token = Jwts.builder()
                .setSubject(userName)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
                .compact();
        UserService userServiceImpl = (UserService) SpringUtils.getBean("userServiceImpl");
        UserDto userDto = userServiceImpl.getUser(userName);

        res.addHeader("Authorization", ((Environment) SpringUtils.getBean("environment")).getProperty("token.prefix")+" "+ token);
        res.addHeader("UserID", userDto.getUsername());

    }

}
