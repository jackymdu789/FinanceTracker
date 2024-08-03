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
import jakarta.persistence.PrePersist;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String tranId;
	String tag;
	BigDecimal amount;
	String tranType;
	LocalDateTime createdAt;
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
	String imageUrl;

	@PrePersist
	protected void onCreate() {
		if (createdAt == null) {
			createdAt = LocalDateTime.now();
		}
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(String accountId) {
		this.account = new Account(accountId);
	}

	public Transaction(String tranId, String tag, BigDecimal amount, String tranType, LocalDateTime createdAt,
			String accountId, String imageUrl) {
		super();
		this.tranId = tranId;
		this.tag = tag;
		this.amount = amount;
		this.tranType = tranType;
		this.createdAt = createdAt;
		this.account = new Account(accountId);
		this.imageUrl = imageUrl;
	}

	public Transaction() {
	}

}
