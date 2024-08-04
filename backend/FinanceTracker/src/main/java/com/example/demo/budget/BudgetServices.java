package com.example.demo.budget;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetServices {

    @Autowired
    BudgetRepository repo;

    List<Budget> fetchAllAccountId(String accountId){
        return repo.findByAccountAccountId(accountId);
    }

    void insertNewBudget(Budget budget){
        repo.save(budget);
    }

    void updateBudgetByBudgetId(String budgetId, Budget budget){
        budget.setBudgetId(budgetId);
        insertNewBudget(budget);
    }

    Iterable<Budget> findall(){
        return repo.findAll();
    }
}
