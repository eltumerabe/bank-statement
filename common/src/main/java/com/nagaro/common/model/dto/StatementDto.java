package com.nagaro.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatementDto {
    private Long Id;
    private Long AccountId;
    private Date datefield;
    private BigDecimal amount;
}
