package com.example.demo.account;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRespository extends CrudRepository<Account, String> {

}
