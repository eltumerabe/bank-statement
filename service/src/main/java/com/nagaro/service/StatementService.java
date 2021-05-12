package com.nagaro.service;

import com.nagaro.common.model.ui.StatementRest;

import java.util.List;

public interface StatementService {
    List<StatementRest> getStatments(String accountId, String fromDate, String toDate, String fromAmount, String toAmount);

}
