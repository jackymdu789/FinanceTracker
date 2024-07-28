package com.example.demo.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServices {
	
	@Autowired
	AccountRespository respo;
	
	Iterable<Account> getAllAccountDetails() {
		return respo.findAll();
	}
	
	void addNewAccount(Account account) {
		respo.save(account);
	}
	
}
