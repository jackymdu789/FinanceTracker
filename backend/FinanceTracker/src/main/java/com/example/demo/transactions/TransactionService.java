package com.example.demo.transactions;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.account.Account;
import com.example.demo.account.AccountRepository;
import com.example.demo.account.AccountServices;

@Service
public class TransactionService {

	@Autowired
	TransactionRepository repository;

	@Autowired
	AccountServices services;

	public Transaction saveTransaction(Transaction transaction) {
		Transaction tran = repository.save(transaction);
		BigDecimal calc = tran.getTranType().compareTo("income") == 0 ? tran.getAmount()
				: new BigDecimal(-1).multiply(tran.getAmount());
		services.updateBalanceByAccountId(tran.getAccount().getAccountId(), calc);
		return tran;
	}

	public Optional<Transaction> getTransactionById(String tranId) {
		return repository.findById(tranId);

	}

	List<Transaction> getAllTransactions() {
		return repository.findAll().stream().sorted((a, b) -> 
									b.getCreatedAt().compareTo(a.getCreatedAt()))
										.collect(Collectors.toList());
	}

	List<Transaction> getTransactionsByAccountId(String accountId) {
		return repository.findByAccountAccountId(accountId);
	}

}
