package com.example.demo.userGoals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGoalsServices {

    @Autowired
    UserGoalsRepository repository;

    public Iterable<UserGoals> getAllUserGoals() {
        return repository.findAll();
    }

    public void addNewUserGoal(UserGoals userGoal) {
        repository.save(userGoal);
    }
}
