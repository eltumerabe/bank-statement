package com.nagaro.dataaccess.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatementRepository extends PagingAndSortingRepository<StatementRepository, Long> {
}
