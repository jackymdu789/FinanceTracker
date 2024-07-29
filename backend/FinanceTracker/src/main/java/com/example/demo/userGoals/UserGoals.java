package com.example.demo.userGoals;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.example.demo.account.Account;
import com.example.demo.usersDetails.UserDetails;

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
	private String goalId;
	@ManyToOne
	@JoinColumn(name= "accountId")
	private Account userAccount;
	private BigDecimal goalAmount;
	private Timestamp startDate;
	private Timestamp endDate;
	@Enumerated(EnumType.STRING)
	private GoalStatus status;
	
	public UserGoals() {
		
	}
	

	public UserGoals(String goalId, String accountId, BigDecimal goalAmount, Timestamp startDate, Timestamp endDate,
			GoalStatus status) {
		super();
		this.goalId = goalId;
		this.userAccount = new Account(accountId);
		this.goalAmount = goalAmount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}
	
	
}


enum GoalStatus{
	Pending,
	Acquired,
	In_Progress
}

