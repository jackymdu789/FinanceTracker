package com.example.demo.transactions;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
	
	Transaction saveTransaction(Transaction transaction);
	Optional<Transaction> getTransactionById(String tranId);
	List<Transaction> getTransactionsByAccountId(String accountId);

}
