package com.nagaro.service.impl;

import com.nagaro.common.model.dto.UserDto;
import com.nagaro.common.model.entity.Statement;
import com.nagaro.common.model.ui.StatementRest;
import com.nagaro.common.utils.NagaroUtility;
import com.nagaro.dataaccess.repository.StatementRepository;
import com.nagaro.service.StatementService;
import com.nagaro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class StatementServiceImpl implements StatementService {
    @Autowired
    private StatementRepository statementRepository;
    @Autowired
    private NagaroUtility nagaroUtility;
    @Autowired
    private UserService userService;

    @Override
    public List<StatementRest> getStatments(HttpServletRequest request, String accountId, String fromDate, String toDate, String fromAmount, String toAmount) {
        try {
            String userName = nagaroUtility.findUserName(request);
            UserDto user = userService.getUser(userName);
            // retrieve all statements
            List<Statement> statements = statementRepository.getAllByAccountId(accountId);
            List<StatementRest> statementRests = nagaroUtility.mapList(statements);
            if ("admin".equalsIgnoreCase(user.getRole())) {
                boolean isDateRaneFilter = false;
                boolean isAmountRangeFilter = false;
                if (null != fromAmount && null != toAmount) {
                    isAmountRangeFilter = true;
                }
                if (null != fromDate && null != toDate) {
                    isDateRaneFilter = true;
                }
                if (isAmountRangeFilter && isDateRaneFilter) {
                    statementRests = this.statementByDateAndAmountRange(fromDate, toDate, fromAmount, toAmount);
                } else {
                    if (isAmountRangeFilter) {
                        // filter last three month
                        statementRests = this.statementByAmountRange(statementRests, fromAmount, toAmount);
                    } else if (isDateRaneFilter) {
                        // filter last three month
                        statementRests = this.statementByDateRange(statementRests, fromDate, toDate);
                    } else {
                        // filter last three month
                        statementRests = this.lastThreeMonthStatements(statementRests);
                    }
                }
            } else {
                // filter last three month
                statementRests = this.lastThreeMonthStatements(statementRests);
            }
            return statementRests;
        } catch (Exception ex) {
            log.error("---- StatementServiceImpl.getStatments error->", ex);
        }
        return null;
    }

    private List<StatementRest> statementByDateAndAmountRange(String fromDate, String toDate, String fromAmount, String toAmount) {
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private List<StatementRest> statementByDateRange(final List<StatementRest> statementRests, final String fromDate, final String toDate) {
        try {
            final LocalDate startDate = nagaroUtility.stringToDate(fromDate);
            final LocalDate endDate = nagaroUtility.stringToDate(toDate);
            List<StatementRest> collect = statementRests.stream().filter(statementRest -> (statementRest.getDatefield().isAfter(startDate) && statementRest.getDatefield().isBefore(endDate))).collect(Collectors.toList());
            return collect;
        } catch (Exception ex) {
            log.error("---- StatementServiceImpl.statementByDateRange error->", ex);
        }
        return null;
    }

    private List<StatementRest> statementByAmountRange(List<StatementRest> statementRests, String fromAmount, String toAmount) {
        try {
            final BigDecimal startAmount = new BigDecimal(fromAmount);
            final BigDecimal endAmount = new BigDecimal(toAmount);
            List<StatementRest> collect = statementRests.stream().filter(statementRest -> (statementRest.getAmount().compareTo(startAmount) > 0 && statementRest.getAmount().compareTo(endAmount) < 0)).collect(Collectors.toList());
            return collect;
        } catch (Exception ex) {
            log.error("---- StatementServiceImpl.statementByAmountRange error->", ex);
        }
        return null;
    }

    private List<StatementRest> lastThreeMonthStatements(List<StatementRest> statementRests) {
        try {
            LocalDate recentDate = statementRests.stream().map(u -> u.getDatefield()).max(LocalDate::compareTo).get();
            LocalDate earlierDate = recentDate.minusMonths(3);
            return statementRests.stream().filter(statementRest -> (statementRest.getDatefield().isAfter(earlierDate) && statementRest.getDatefield().isBefore(recentDate))).collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("---- StatementServiceImpl.lastThreeMonthStatements error->", ex);
        }
        return null;
    }
}