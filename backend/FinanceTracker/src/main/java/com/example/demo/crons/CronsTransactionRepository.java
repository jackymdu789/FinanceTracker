package com.example.demo.crons;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CronsTransactionRepository extends CrudRepository<CronsTransaction, String> {
	
	List<CronsTransaction> findByDate(Integer date);
}
