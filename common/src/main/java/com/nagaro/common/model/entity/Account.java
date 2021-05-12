package com.nagaro.common.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    @org.springframework.data.annotation.Id
    private Integer Id;
    private String AccountType;
    private String AccountNumber;
}
