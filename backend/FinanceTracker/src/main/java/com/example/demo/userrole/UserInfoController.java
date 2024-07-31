package com.example.demo.userrole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1/userinfo")
public class UserInfoController {
	
	@Autowired
	UserInfoServices service;
	
	
	@GetMapping("/")
	public String getMethodName() {
		return new String("hello world!");
	}
	
	
	@PostMapping("/add/{userId}")
	void addUser(@RequestBody UserInfo userInfo, @PathVariable String userId) {
		userInfo.setUserDetails(userId);
		service.addUser(userInfo);
	}
}
