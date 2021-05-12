package com.nagaro.service.impl;

import com.nagaro.common.model.entity.Statement;
import com.nagaro.common.model.ui.StatementRest;
import com.nagaro.common.utils.NagaroUtility;
import com.nagaro.dataaccess.repository.StatementRepository;
import com.nagaro.service.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StatementServiceImpl implements StatementService {
    @Autowired
    private StatementRepository statementRepository;
    @Autowired
    private NagaroUtility nagaroUtility;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<StatementRest> getStatments(String accountId, String fromDate, String toDate, String fromAmount, String toAmount) {
        try {
            // retrieve all statements
            List<Statement> statements = statementRepository.getAllByAccountId(accountId);
            List<StatementRest> statementRests = nagaroUtility.mapList(statements);
            boolean isDateRaneFilter = false;
            boolean isAmountRangeFilter = false;
            if (null != fromAmount && null != toAmount) {
                isAmountRangeFilter = true;
            }
            if (null != fromDate && null != toDate) {
                isAmountRangeFilter = true;
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
            return statementRests;
        } catch (Exception ex) {
            ex.printStackTrace();
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
            statementRests.stream().filter(statementRest -> (statementRest.getDatefield().isAfter(startDate) && statementRest.getDatefield().isBefore(endDate))).collect(Collectors.toList());
        } catch (Exception ex) {
            ex.printStackTrace();
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
            ex.printStackTrace();
        }
        return null;
    }

    private List<StatementRest> lastThreeMonthStatements(List<StatementRest> statementRests) {
        try {
            LocalDate recentDate = statementRests.stream().map(u -> u.getDatefield()).max(LocalDate::compareTo).get();
            LocalDate earlierDate = recentDate.minusMonths(3);
            return statementRests.stream().filter(statementRest -> (statementRest.getDatefield().isAfter(earlierDate) && statementRest.getDatefield().isBefore(recentDate))).collect(Collectors.toList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}