package com.example.demo.userGoals;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userGoals")
public class UserGoalsController {

    @Autowired
    private UserGoalsServices service;

    @GetMapping("/")
    public Iterable<UserGoals> getAllUserGoals() {
        return service.getAllUserGoals();
    }
    

	@GetMapping("/{goalsId}")
	public Optional<UserGoals> getUserGoals(@PathVariable String goalsId) {
		return service.getUserGoals(goalsId);
	}
	
    @PostMapping("/")
    public void addNewUserGoals(@RequestBody UserGoals userGoals) {
        service.addNewUserGoals(userGoals);
    }
    
    @PutMapping("/{goalsId}")
	public void updateUserGoals(@RequestBody UserGoals userGoals, @PathVariable String goalsId) {
    	userGoals.setGoalsId(goalsId);
		service.updateUserGoals(userGoals);
	}

	@DeleteMapping("/{goalsId}")
	public void deleteUserGoals(@PathVariable String goalsId) {
		service.deleteUserGoals(goalsId);
	}
}
