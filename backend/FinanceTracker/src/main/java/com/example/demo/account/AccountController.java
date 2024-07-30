package com.example.demo.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

	@Autowired
	AccountServices services;

	@GetMapping("/")
	public String home() {
		return "Welcome to Finance Tracker";
	}

	@PostMapping("/{userId}")
	public void postMethodName(@RequestBody Account account, @PathVariable String userId) {
		account.setUserDetails(userId);
		services.addNewAccount(account);
	}
	
	@GetMapping("/all")
	Iterable<Account> getAllAccountDetails() {
		return services.getAllAccountDetails();
	}
	

}
