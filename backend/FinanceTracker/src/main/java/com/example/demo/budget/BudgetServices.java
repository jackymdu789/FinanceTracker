package com.example.demo.budget;

import java.util.List;
import java.util.Optional;

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
        Optional<Budget> existingBudget = repo.findByAccountAccountIdAndBudgetCategoryAndFrequency(budget.getAccount().getAccountId(), budget.getBudgetCategory(), budget.getFrequency());
        if(existingBudget.isPresent()){
            Budget updateBudget = existingBudget.get();
            updateBudget.setBudgetAmt(budget.getBudgetAmt());
            repo.save(updateBudget);
        }else{
            repo.save(budget);
        }
    }

    void updateBudgetByBudgetId(String budgetId, Budget budget){
        budget.setBudgetId(budgetId);
        insertNewBudget(budget);
    }

    Iterable<Budget> findall(){
        return repo.findAll();
    }
}
