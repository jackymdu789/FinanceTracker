package com.example.demo.transactions;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
	@Autowired
	TransactionService service;
	
	@PostMapping("/create")
	ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
		Transaction savedTransaction = service.saveTransaction(transaction);
		return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
		}
	
	
	@GetMapping("/{tranId}")
	public ResponseEntity<Transaction> getTransactionById(@PathVariable String tranId) {
		Optional<Transaction> transaction= service.getTransactionById(tranId);
		return transaction.map(value-> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
				
	}
	
	
	@GetMapping("/{accountId}")
	ResponseEntity<List<Transaction>> getTransactionByAccountId(@PathVariable(value="accountId") String accountId) {
		List<Transaction> transactions= service.getTransactionsByAccountId(accountId);
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}

}
