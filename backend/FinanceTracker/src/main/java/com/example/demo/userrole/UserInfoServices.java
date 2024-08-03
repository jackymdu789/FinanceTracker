package com.example.demo.userrole;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServices {
	
	@Autowired
	UserInfoRepository repo;
	
	@Autowired
	PasswordEncoder encoder;
	
	void addUser(UserInfo userInfo) {
		userInfo.setPassword(encoder.encode(userInfo.getPassword()));
		repo.save(userInfo);
	}

	Optional<UserInfo> getByUserName(String username){
		return repo.findByUsername(username);
	}
}
