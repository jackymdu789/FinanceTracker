package com.example.demo.transactions;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.account.Account;

@Service
public class TransactionService  {
	
	@Autowired
	TransactionRepository repository;

	public void saveTransaction(Transaction transaction) {
		repository.save(transaction);
	}

	public Optional<Transaction> getTransactionById(String tranId) {
		return repository.findById(tranId);
		
	}
	
	List<Transaction> getAllTransactions() {
		return repository.findAll();
	}
	
	List<Transaction> getTransactionsByAccountId(String accountId) {
		return repository.findByAccountAccountId(accountId);
	}

}
