package com.example.demo.userGoals;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGoalsRepository extends CrudRepository<UserGoals, String>{
	
}
