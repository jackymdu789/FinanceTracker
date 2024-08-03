package com.example.demo.account;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class AccountServices {
	
	@Autowired
	AccountRepository respo;
	
	public Iterable<Account> getAllAccountDetails() {
		return respo.findAll();
	}
	
	public void addNewAccount(Account account) {
		respo.save(account);
	}

	public Optional<Account> getAccountByUserId(String userId){
		return respo.findByUserDetailsUserId(userId);
	}

	public Optional<Account> getAllAccountDetailsById(@PathVariable String accountId) {
		return respo.findById(accountId);
	}
	
}
