package com.example.demo.usersDetails;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class UserDetailsServices {

	@Autowired
	UserDetailsRepository respo;

	Iterable<UserDetails> getAllUserDetails() {
		return respo.findAll();
	}

	void addingNewUserDetails(UserDetails userDetails) {
		respo.save(userDetails);
	}

	void addingRelationShipForUser(List<UserDetails> userIds, String userId) {
		UserDetails userDetails = respo.findById(userId).orElseThrow();
		userDetails.setListOfRelationAccount(userIds);
		respo.save(userDetails);
	}
	
	void deleteUserDetailsById(String userId) {
		respo.deleteById(userId);
	}
}
