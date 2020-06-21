package com.example.demo.model;

import java.time.Instant;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String uid;
	@Column
	private double balance;
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	
	private List<Transaction> transList=new ArrayList();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public List<Transaction> getTransList() {
		return transList;
	}
	public void setTransList(List<Transaction> transList) {
		this.transList = transList;
	}
	
	
}
