package com.example.demo.bill;

import java.time.LocalDate;

import com.example.demo.account.Account;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Account account;
    private String name;
    private double amount;
    private LocalDate dueDate;
    private boolean isPaid;
    
    Bill(){
        
    }
    public Bill(Long id, String accountId, String name, double amount, LocalDate dueDate, boolean isPaid) {
        this.id = id;
        this.account = new Account(accountId);
        this.name = name;
        this.amount = amount;
        this.dueDate = dueDate;
        this.isPaid = isPaid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(String accountId) {
        this.account = new Account(accountId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    
}
