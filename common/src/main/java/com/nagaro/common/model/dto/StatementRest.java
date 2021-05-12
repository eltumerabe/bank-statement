package com.nagaro.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatementRest {
    private Long Id;
    private Long AccountId;
    private String datefield;
    private String amount;
}
