package com.nagaro.dataaccess.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Statement {
    @org.springframework.data.annotation.Id
    private Long Id;
    private Long AccountId;
    private String datefield;
    private String amount;
}
