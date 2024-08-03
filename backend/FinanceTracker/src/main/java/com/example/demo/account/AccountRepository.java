package com.example.demo.account;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

    Optional<Account> findByUserDetailsUserId(String userId);

}
