package com.nagaro.service;

import com.nagaro.common.model.ui.StatementRest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface StatementService {
    List<StatementRest> getStatments(HttpServletRequest request, String accountId, String fromDate, String toDate, String fromAmount, String toAmount);

}
