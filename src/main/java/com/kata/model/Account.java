package com.kata.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kata.error.IlegalTransException;
@Entity
public class Account {
	private static final Logger logger = LogManager.getLogger(Account.class);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accountId;

	private double balance;

	@NotNull(message = "Please provide a customer.")
	@ManyToOne
	private Customer customer;

	public Account() {
	}

	public Account(@NotNull(message = "Please provide a customer.") Customer customer) {
		super();
		this.customer = customer;
	}

	public long getAccountId() {
		return accountId;
	}
	
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double amount) {
		this.balance = amount;
	}

	public void add(double amount) throws IlegalTransException {
		if (amount <= 0.01) {
			logger.error("Ilegale deposite amount :" + amount);
			throw new IlegalTransException("Ilegale deposite amount, amount should be superior to 0.01");
		}
		this.balance += amount;
	}

	public void withdraw(double amount) throws IlegalTransException {
		if (amount <= 0) {
			throw new IlegalTransException("withdraw amount should not be negative.");
		}
		if (amount > getBalance()) {
			throw new IlegalTransException("withdraw amount should not be superior to the balance.");
		}
		this.balance -= amount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountId ^ (accountId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountId != other.accountId)
			return false;
		return true;
	}
	
	

}
