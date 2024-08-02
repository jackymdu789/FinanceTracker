package com.example.demo.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.account.Account;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
	
	List<Transaction> findByAccountAccountId(String accountId);
	
}
