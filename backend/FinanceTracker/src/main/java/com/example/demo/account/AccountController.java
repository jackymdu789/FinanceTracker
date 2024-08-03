package com.example.demo.account;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/v1/account")
@CrossOrigin(origins = "*")
public class AccountController {

	@Autowired
	AccountServices services;

	@GetMapping("/")
	public String home() {
		return "Welcome to Finance Tracker";
	}

	@PostMapping("/add/{userId}")
	public void postMethodName(@RequestBody Account account, @PathVariable String userId) {
		account.setUserDetails(userId);
		services.addNewAccount(account);
	}
	
	@GetMapping("/all")
	Iterable<Account> getAllAccountDetails() {
		return services.getAllAccountDetails();
	}

	@GetMapping("/{accountId}")
	public Optional<Account> getAllAccountDetailsById(@PathVariable String accountId) {
		return services.getAllAccountDetailsById(accountId);
	}

}
