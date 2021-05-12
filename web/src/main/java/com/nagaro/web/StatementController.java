package com.nagaro.web;

import com.nagaro.common.model.ui.StatementRest;
import com.nagaro.common.utils.NagaroUtility;
import com.nagaro.service.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statements")
public class StatementController {
    @Autowired
    private StatementService statementService;
    @Autowired
    private NagaroUtility nagaroUtility;

    @GetMapping
    public List<StatementRest> statements(@RequestParam(value = "accountId", required = true) String accountId,
                                          @RequestParam(value = "fromDate", required = false) String fromDate,
                                          @RequestParam(value = "toDate", required = false) String toDate,
                                          @RequestParam(value = "fromAmount", required = false) String fromAmount,
                                          @RequestParam(value = "toAmount", required = false) String toAmount
    ) {
        List<StatementRest> statments = statementService.getStatments(accountId, fromDate, toDate, fromAmount, toAmount);
        return statments;
    }
}