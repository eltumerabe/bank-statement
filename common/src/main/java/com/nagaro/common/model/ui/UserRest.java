package com.nagaro.common.model.ui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRest {
    private Integer Id;
    private String username;
    private String password;
    private String role;
}
