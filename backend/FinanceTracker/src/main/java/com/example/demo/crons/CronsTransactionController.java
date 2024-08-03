package com.example.demo.crons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/crons")
@CrossOrigin(origins = "*")
public class CronsTransactionController {
	@Autowired
	CronsTransactionServices services;
	
	@GetMapping("/")
	Iterable<CronsTransaction> FetchAllData() {
		return services.FetchAllData();
	}
	
	@PostMapping("/add/{tranId}")
	void insertData(@RequestBody CronsTransaction cronsTransaction, @PathVariable String tranId) {
		cronsTransaction.setTransaction(tranId);
		services.insertData(cronsTransaction);
	}
	
	@GetMapping("/runCrons")
	void runCrons() {
		services.runCrons();
	}
}
