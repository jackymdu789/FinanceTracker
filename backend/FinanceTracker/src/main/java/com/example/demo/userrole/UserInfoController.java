package com.example.demo.userrole;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.account.AccountServices;
import com.example.demo.auth.AuthRequest;
import com.example.demo.config.JwtService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1/userinfo")
@CrossOrigin(origins = "*")
public class UserInfoController {

	@Autowired
	UserInfoServices service;

	@Autowired
	AccountServices accountService;

	@Autowired
	JwtService jwtservice;

	@Autowired
	AuthenticationManager authenticationManager;

	@GetMapping("/")
	public String getMethodName() {
		return new String("hello world!");
	}

	@PostMapping("/add/{userId}")
	void addUser(@RequestBody UserInfo userInfo, @PathVariable String userId) {
		userInfo.setUserDetails(userId);
		service.addUser(userInfo);
	}

	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody AuthRequest authrequest) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(
						authrequest.getUsername(), authrequest.getPassword()));
		if(authentication.isAuthenticated()) {
			Optional<UserInfo> userInfo = service.getByUserName(authrequest.getUsername());
			if(userInfo.isPresent()){
				String userId = userInfo.get().getUserDetails().getUserId();
				String accountId = accountService.getAccountByUserId(userId).get().getAccountId();
				return jwtservice.generateToken(authrequest.getUsername(), accountId);
			}else{
				throw new UsernameNotFoundException("user not found!");
			}
		} else {
			throw new UsernameNotFoundException("invalid user request !");
		}
	}

}
