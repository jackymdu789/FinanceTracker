package com.example.demo.userGoals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userGoals")
public class UserGoalsController {

    @Autowired
    private UserGoalsServices service;

    @GetMapping
    public Iterable<UserGoals> getAllUserGoals() {
        return service.getAllUserGoals();
    }

    @PostMapping
    public void addNewUserGoal(@RequestBody UserGoals userGoal) {
        service.addNewUserGoal(userGoal);
    }
}
