package com.nagaro.common.model.ui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatementDto {
    private Long Id;
    private Long AccountId;
    private String datefield;
    private String amount;
}
