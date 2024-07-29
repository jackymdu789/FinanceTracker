package com.example.demo.account;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AccountController {
	
	@Autowired
	AccountServices services;
	
	@GetMapping("/")
	public String home() {
		return "Welcome to Finance Tracker";
	}
	
	@PostMapping
    public void addNewAccount(@RequestBody Account account) {
        services.addNewAccount(account);
    }
	
	@GetMapping("/{accountId}") 
    public Optional<Account> getUserAccount(@PathVariable String accountId) {
		return services.getUserAccount(accountId);
	}
    
	@PutMapping("/{accountId}")
    public void updateUserAccount(@RequestBody Account account, @PathVariable String accountId) {
		account.setAccountId(accountId);
		services.updateUserAccount(account);
	}
    
	@DeleteMapping("/{accountId}")
    public void deleteUserAccount(@PathVariable String accountId) {
		services.deleteUserAccount(accountId);
	}

}
