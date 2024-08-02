package com.example.demo.usersDetails;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/userdetails")
@CrossOrigin(origins = "*")
public class UserDetailsController {

	@Autowired
	UserDetailsServices service;

	@GetMapping("/")
	String home() {
		return "welcome to user details";
	}

	@GetMapping("/all")
	Iterable<UserDetails> getAllUserDetails() {
		return service.getAllUserDetails();
	}

	@PostMapping("/")
	void addingNewUserDetails(@RequestBody UserDetails userDetails) {
		service.addingNewUserDetails(userDetails);
	}

	@PutMapping("/{userId}")
	void addingRelationShipForUser(@RequestBody List<UserDetails> userIds, @PathVariable String userId) {
		service.addingRelationShipForUser(userIds, userId);
	}
	
	@DeleteMapping("/{userId}")
	void deleteUserDetailsById(@PathVariable String userId) {
		service.deleteUserDetailsById(userId);
	}
}
