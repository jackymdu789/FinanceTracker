package com.example.demo.usersDetails;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.account.Account;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String userId;
	String userName;
	String dob;
	String age;
	BigDecimal salary;
	@ManyToMany
	@JoinTable(name = "userRelationshipTable", 
					joinColumns = @JoinColumn(name = "userId"),
					inverseJoinColumns = @JoinColumn(name = "otherUserId"))
	List<UserDetails> listOfRelationAccount = new ArrayList<>();
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public List<UserDetails> getListOfRelationAccount() {
		return listOfRelationAccount;
	}

	public void setListOfRelationAccount(List<UserDetails> listOfRelationAccount) {
		this.listOfRelationAccount = listOfRelationAccount;
	}

	public UserDetails(String userId) {
		this(userId, null, null, null, null, null);
	}
	
	public UserDetails(String userId, List<UserDetails> userIds) {
		this(userId, null, null, null, null, userIds);
	}

	public UserDetails(String userId, String userName, String dob, String age, BigDecimal salary,
			List<UserDetails> listOfRelationAccount) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.dob = dob;
		this.age = age;
		this.salary = salary;
		this.listOfRelationAccount = listOfRelationAccount;
	}
	UserDetails() {
	}

}
