package com.nagaro.dataaccess.repository;

import com.nagaro.common.model.entity.Statement;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementRepository extends PagingAndSortingRepository<Statement, Long> {
    @Query("SELECT * FROM STATEMENT WHERE ACCOUNT_ID=:accountId")
    List<Statement> getAllByAccountId(String accountId);
}