package com.example.demo.transactions;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

	@Autowired
	TransactionService service;
	
	@GetMapping("/")
	public String getMethodName() {
		return new String("hello world!");
	}
	

	@PostMapping("/transaction/{accountId}")
	void createTransaction(@RequestBody Transaction transaction, @PathVariable String accountId) {
		transaction.setAccount(accountId);
		service.saveTransaction(transaction);
	}
	
	@GetMapping("/all")
	List<Transaction> getAllTransactions() {
		return service.getAllTransactions();
	}

	@GetMapping("/{tranId}")
	public Optional<Transaction> getTransactionById(@PathVariable String tranId) {
		return service.getTransactionById(tranId);

	}
	
	@GetMapping("account/{accountId}")
	List<Transaction> getTransactionsByAccountId(@PathVariable String accountId) {
		return service.getTransactionsByAccountId(accountId);
	}

}
