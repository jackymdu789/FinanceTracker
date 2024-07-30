package com.example.demo.transactions;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.demo.account.Account;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String tranId;
	String tag;
	BigDecimal amount;
	LocalDateTime date;
	
	@ManyToOne
	@JoinColumn(name="accountId", nullable= false)
	private Account account;
	
	

	public Transaction() {
		super();
	}



	public Transaction(String tranId, String tag, BigDecimal amount, LocalDateTime date, String accountId) {
		super();
		this.tranId = tranId;
		this.tag = tag;
		this.amount = amount;
		this.date = date;
		this.account= new Account(accountId);
		
		
	}



	public String getTranId() {
		return tranId;
	}



	public void setTranId(String tranId) {
		this.tranId = tranId;
	}



	public String getTag() {
		return tag;
	}



	public void setTag(String tag) {
		this.tag = tag;
	}



	public BigDecimal getAmount() {
		return amount;
	}



	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}



	public LocalDateTime getDate() {
		return date;
	}



	public void setDate(LocalDateTime date) {
		this.date = date;
	}



	public Account getAccount() {
		return account;
	}



	public void setAccount(String accountId) {
		this.account = account;
	}



	@Override
	public String toString() {
		return "Transaction [tranId=" + tranId + ", tag=" + tag + ", amount=" + amount + ", date=" + date + ", account="
				+ account + "]";
	}
	
	
	
	
	
	
	

}
