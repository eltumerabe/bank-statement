package com.nagaro.dataaccess.repository;

import com.nagaro.dataaccess.entity.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT * FROM USER WHERE USERNAME=:username AND PASSWORD=:password")
    User getByUsernameAndPassword(String username, String password);
}
