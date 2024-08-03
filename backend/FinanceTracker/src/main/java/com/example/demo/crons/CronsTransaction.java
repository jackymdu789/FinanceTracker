package com.example.demo.crons;

import java.util.List;

import com.example.demo.transactions.Transaction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class CronsTransaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String cronsId;
	@OneToOne
	Transaction transaction;
	Integer date;
	
		
	
	public CronsTransaction(String cronsId, String transactionId, Integer date) {
		this.cronsId = cronsId;
		this.transaction = new Transaction(transactionId);
		this.date = date;
	}
	public CronsTransaction() {
	}
	public String getCronsId() {
		return cronsId;
	}
	public void setCronsId(String cronsId) {
		this.cronsId = cronsId;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(String transactionId) {
		this.transaction = new Transaction(transactionId);
	}
	public Integer getDate() {
		return date;
	}
	public void setDate(Integer date) {
		this.date = date;
	}
	
	
	
}
