package com.example.demo.account;

import java.math.BigDecimal;

import com.example.demo.usersDetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String accountId;
	@ManyToOne
	@JoinColumn(name="userId", nullable=false)
	UserDetails userDetails;
	@Column(nullable=false)
	BigDecimal balance;
	@Column(nullable=false)
	BigDecimal savingAmount;
	@Column(nullable=false)
	Integer goalScore;
	
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
			Integer goalScore) {
		super();
		this.accountId = accountId;
		this.userDetails = new UserDetails(userId);
		this.balance = balance;
		this.savingAmount = savingAmount;
		this.goalScore = goalScore;
	}
	public Account(String accountId) {
		this.accountId = accountId;
	}

}
