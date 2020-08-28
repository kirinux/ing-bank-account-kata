package com.kata.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long transactionId;

	@NotNull(message = "Please provide a account.")
	@ManyToOne
	@JsonIgnore
	private Account account;
	
	private ActionType actionType;
	private double amount;
	private LocalDateTime dateTime;

	public Transaction() {
	}

	public Transaction(@NotNull(message = "Please provide a account.") Account account, ActionType actionType,
			double amount, LocalDateTime dateTime) {
		super();
		this.account = account;
		this.actionType = actionType;
		this.amount = amount;
		this.dateTime = dateTime;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Account getAccount() {
		return account;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

}
