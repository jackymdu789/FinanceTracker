package com.example.demo.userGoals;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserGoalsServices {

    @Autowired
    UserGoalsRepository repository;

    public Iterable<UserGoals> getAllUserGoals() {
        return repository.findAll();
    }

    public void addNewUserGoals(UserGoals userGoals) {
        repository.save(userGoals);
    }
    
    public Optional<UserGoals> getUserGoals(String goalsId) {
		return repository.findById(goalsId);
	}
    
    public void updateUserGoals( UserGoals userGoals) {
    	repository.save(userGoals);
	}
    
    public void deleteUserGoals( String goalsId) {
		repository.deleteById(goalsId);
	}

	
}
