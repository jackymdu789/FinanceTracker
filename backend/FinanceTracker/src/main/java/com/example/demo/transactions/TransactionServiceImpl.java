package com.example.demo.transactions;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService  {
	
	@Autowired
	TransactionRepository repository;

	@Override
	public Transaction saveTransaction(Transaction transaction) {
		return repository.save(transaction);
	}

	@Override
	public Optional<Transaction> getTransactionById(String tranId) {
		return repository.findById(tranId);
		
	}

	@Override
	public List<Transaction> getTransactionsByAccountId(String accountId) {
		return repository.custFindByAccountId(accountId);
		
	}
	
	
	
	

}
