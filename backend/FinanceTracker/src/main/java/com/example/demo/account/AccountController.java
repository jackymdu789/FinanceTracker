package com.example.demo.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
	
	@Autowired
	AccountServices services;
	
	@GetMapping("/")
	public String home() {
		return "Welcome to Finance Tracker";
	}
	
}
