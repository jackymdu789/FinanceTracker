package com.example.demo.crons;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.transactions.Transaction;
import com.example.demo.transactions.TransactionService;

@Service
public class CronsTransactionServices {
	@Autowired
	CronsTransactionRepository repos;

	@Autowired
	TransactionService Transervice;

	Iterable<CronsTransaction> FetchAllData() {
		return repos.findAll();
	}

	void insertData(CronsTransaction cronsTransaction) {
		repos.save(cronsTransaction);
	}
	
	@Scheduled(cron = "0 0 12 * * ?")
	void runCrons() {
		Integer date = LocalDate.now().getDayOfMonth();
		List<CronsTransaction> cronsTask = repos.findByDate(date);
		cronsTask.stream().forEach(cronsTransaction -> {
			System.out.println(cronsTransaction.getTransaction().getTranId());
			Transaction transaction = cronsTransaction.getTransaction();
			Transervice.saveTransaction(new Transaction(transaction.getTag(), transaction.getAmount(),
					transaction.getTranType(), transaction.getAccount().getAccountId(), transaction.getImageUrl()));
		});

	}

}
