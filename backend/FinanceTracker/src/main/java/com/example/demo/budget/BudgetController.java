package com.example.demo.budget;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.account.Account;

@RestController
@RequestMapping("/api/v1/budget")
@CrossOrigin(origins = "*")
public class BudgetController {

    @Autowired
    BudgetServices ser;
    @Autowired
    BudgetRepository repo;

    @GetMapping("/{accountId}")
    List<Budget> fetchAllAccountId(@PathVariable String accountId){
        return ser.fetchAllAccountId(accountId);
    }

    @PostMapping("/{accountId}")
    void insertNewBudget(@RequestBody Budget budget, @PathVariable String accountId){
        budget.setAccount(accountId);
        ser.insertNewBudget(budget);
    }

    @PutMapping("/{budgetId}")
    void updateBudgetByBudgetId(@PathVariable String budgetId,@RequestBody Budget budget){
        Account ac = repo.findById(budgetId).orElseGet(null).getAccount();
        budget.setAccount(ac.getAccountId());
        ser.updateBudgetByBudgetId(budgetId, budget);
    }

    @GetMapping("/all")
    public Iterable<Budget> getMethodName() {
        return ser.findall();
    }
    
}
