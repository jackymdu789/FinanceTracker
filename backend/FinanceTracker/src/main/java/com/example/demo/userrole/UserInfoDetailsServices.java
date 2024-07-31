package com.example.demo.userrole;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserInfoDetailsServices implements UserDetailsService {

	@Autowired
	UserInfoRepository respo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfo= respo.findByUsername(username);
		return userInfo.map(UserInfoDetails::new)
						.orElseThrow(() -> new UsernameNotFoundException("user is not found"));
		
	}

}
