package com.example.demo.account;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.userGoals.UserGoals;

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
	
	 
    public Optional<Account> getUserAccount(String accountId) {
		return respo.findById(accountId);
	}
    
    public void updateUserAccount( Account account) {
    	respo.save(account);
	}
    
    public void deleteUserAccount( String accountId) {
    	respo.deleteById(accountId);
	}


}
