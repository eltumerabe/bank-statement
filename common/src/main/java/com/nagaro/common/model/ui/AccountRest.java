package com.nagaro.common.model.ui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRest {
    private Long Id;
    private String AccountType;
    private String AccountNumber;
}
