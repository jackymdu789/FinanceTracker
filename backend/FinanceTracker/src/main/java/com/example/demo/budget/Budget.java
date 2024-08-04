package com.example.demo.budget;

import java.math.BigDecimal;

import com.example.demo.account.Account;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String budgetId;
    public BigDecimal getBudgetAmt() {
        return budgetAmt;
    }
    public void setBudgetAmt(BigDecimal budgetAmt) {
        this.budgetAmt = budgetAmt;
    }
    String budgetCategory;
    BigDecimal budgetAmt;
    @OneToOne
    Account account;
    String frequency;

    

    public String getFrequency() {
        return frequency;
    }
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
    public String getBudgetId() {
        return budgetId;
    }
    public void setBudgetId(String budgetId) {
        this.budgetId = budgetId;
    }
    public String getBudgetCategory() {
        return budgetCategory;
    }
    public void setBudgetCategory(String budgetCategory) {
        this.budgetCategory = budgetCategory;
    }
    public Account getAccount() {
        return account;
    }
    public void setAccount(String accountId) {
        this.account = new Account(accountId);
    }
    public Budget(String budgetId, String budgetCategory, String accountId, BigDecimal budgetAmt, String frequency) {
        this.budgetId = budgetId;
        this.budgetCategory = budgetCategory;
        this.account = new Account(accountId);
        this.budgetAmt = budgetAmt;
        this.frequency = frequency;
    }
    Budget(){

    }

}
