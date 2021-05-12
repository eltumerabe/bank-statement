package com.nagaro.common.utils;

import com.nagaro.common.model.entity.Statement;
import com.nagaro.common.model.ui.StatementRest;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
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
            log.error("---- StatementServiceImpl.stringToDate error->", ex);
        }
        return null;
    }

    public String findUserName(HttpServletRequest req) {
        try {
            Environment environment = (Environment) SpringUtils.getBean("environment");
            String authorization = environment.getProperty("header.authorization");
            String token = req.getHeader(authorization);
            if (token != null) {
                token = token.replace(environment.getProperty("token.prefix"), "");
                String user = Jwts.parser()
                        .setSigningKey(environment.getProperty("token.secret"))
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject();
                if (null != user) {
                    return user;
                }
            }
        } catch (Exception ex) {
            log.error("---- StatementServiceImpl.findUserName error->", ex);
        }
        return null;
    }
}