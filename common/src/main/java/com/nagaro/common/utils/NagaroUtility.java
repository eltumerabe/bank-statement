package com.nagaro.common.utils;

import com.nagaro.common.model.entity.Statement;
import com.nagaro.common.model.ui.StatementRest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NagaroUtility {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public List<StatementRest> mapList(List<Statement> statements) {
        List<StatementRest> statementRests = new ArrayList<>();
        statements.stream().parallel().forEach(statement -> {
            StatementRest statementRest = new StatementRest();
            statementRest.setAccountId(bCryptPasswordEncoder.encode(String.valueOf(statement.getAccountId())));
            statementRest.setAmount(new BigDecimal(statement.getAmount()));
            statementRest.setDatefield(this.stringToDate(statement.getDatefield()));
            statementRests.add(statementRest);
        });
        return statementRests;
    }

    public LocalDate stringToDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try {
            return LocalDate.parse(date, formatter);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}