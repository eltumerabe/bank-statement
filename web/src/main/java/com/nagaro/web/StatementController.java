package com.nagaro.web;

import com.nagaro.common.exception.EntityNotFoundException;
import com.nagaro.common.model.ui.StatementRest;
import com.nagaro.service.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/statements")
public class StatementController {
    @Autowired
    private StatementService statementService;

    @GetMapping
    public List<StatementRest> statements(HttpServletRequest request,@RequestParam(value = "accountId", required = true) @Valid  String accountId,
                                          @RequestParam(value = "fromDate", required = false) String fromDate,
                                          @RequestParam(value = "toDate", required = false) String toDate,
                                          @RequestParam(value = "fromAmount", required = false) String fromAmount,
                                          @RequestParam(value = "toAmount", required = false) String toAmount
    ) throws EntityNotFoundException {
        List<StatementRest> statments = statementService.getStatments(request,accountId, fromDate, toDate, fromAmount, toAmount);
        return statments;
    }
}