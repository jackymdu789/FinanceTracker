package com.example.demo.budget;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BudgetRepository extends CrudRepository<Budget,String> {
    
    List<Budget> findByAccountAccountId(String accoundId);

    Optional<Budget> findByAccountAccountIdAndBudgetCategoryAndFrequency(String accoundId,String budgetCategory, String frequency);
}
