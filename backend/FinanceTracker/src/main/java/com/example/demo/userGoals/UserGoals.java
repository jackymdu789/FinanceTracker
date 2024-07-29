package com.example.demo.userGoals;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.example.demo.account.Account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="userGoals")
public class UserGoals {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	
	private String goalsId;
	@ManyToOne
	@JoinColumn(name= "accountId", nullable=false)
	private Account userAccount;
	@Column(nullable=false)
	private BigDecimal goalsAmount;
	@Column(nullable=false)
	private Timestamp startDate;
	@Column(nullable=false)
	private Timestamp endDate;
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private GoalsStatus status;
	
	public UserGoals() {
		
	}
	

	public UserGoals(String goalsId, String accountId, BigDecimal goalsAmount, Timestamp startDate, Timestamp endDate,
			GoalsStatus status) {
		super();
		this.goalsId = goalsId;
		this.userAccount = new Account(accountId);
		this.goalsAmount = goalsAmount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}


	public String getGoalsId() {
		return goalsId;
	}


	public void setGoalsId(String goalsId) {
		this.goalsId = goalsId;
	}


	public Account getUserAccount() {
		return userAccount;
	}


	public void setUserAccount(Account userAccount) {
		this.userAccount = userAccount;
	}


	public BigDecimal getGoalsAmount() {
		return goalsAmount;
	}


	public void setGoalsAmount(BigDecimal goalsAmount) {
		this.goalsAmount = goalsAmount;
	}


	public Timestamp getStartDate() {
		return startDate;
	}


	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}


	public Timestamp getEndDate() {
		return endDate;
	}


	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}


	public GoalsStatus getStatus() {
		return status;
	}


	public void setStatus(GoalsStatus status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "UserGoals [goalId=" + goalsId + ", userAccount=" + userAccount + ", goalsAmount=" + goalsAmount
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + "]";
	}

	
	
}


enum GoalsStatus{
	Pending,
	Acquired,
	In_Progress
}

