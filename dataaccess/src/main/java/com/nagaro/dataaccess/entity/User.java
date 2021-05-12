package com.nagaro.dataaccess.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @org.springframework.data.annotation.Id
    private Integer ID;
    private String username;
    private String password;
}
