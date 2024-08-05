package com.example.demo.account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.demo.usersDetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String accountId;
	@ManyToOne
	UserDetails userDetails;
	BigDecimal balance;
	BigDecimal savingAmount;
	Integer goalScore;
	LocalDateTime createdAt;
	
	@PrePersist
	protected void onCreate() {
		if (createdAt == null) {
			createdAt = LocalDateTime.now();
		}
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Account() {
	}
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public UserDetails getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(String userId) {
		this.userDetails = new UserDetails(userId);
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getSavingAmount() {
		return savingAmount;
	}
	public void setSavingAmount(BigDecimal savingAmount) {
		this.savingAmount = savingAmount;
	}
	public Integer getGoalScore() {
		return goalScore;
	}
	public void setGoalScore(Integer goalScore) {
		this.goalScore = goalScore;
	}
	public Account(String accountId, String userId, BigDecimal balance, BigDecimal savingAmount,
			Integer goalScore,LocalDateTime createdAt) {
		super();
		this.accountId = accountId;
		this.userDetails = new UserDetails(userId);
		this.balance = balance;
		this.savingAmount = savingAmount;
		this.goalScore = goalScore;
		this.createdAt = createdAt;
	}
	
	public Account(String accountId) {
		this(accountId, null, null, null, null, null);
	}

}
