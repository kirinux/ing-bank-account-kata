package com.example.demo.service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Account;
import com.example.demo.model.Client;
import com.example.demo.model.Transaction;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.TransactionRepository;

import org.apache.commons.lang3.*;
@Transactional
@Service
public class ClientService {
	@Autowired
	private ClientRepository clientrepo;
	@Autowired
	private AccountRepository accountrepo;
	@Autowired
	private TransactionRepository transrepo;
	public Client addClient(Client c) {
		
		return clientrepo.save(c);
	}
	
	public List<Client> getAllClient(){
		return clientrepo.findAll();
	}
	public Client updateClient(Client c) {
		return clientrepo.save(c);
		
	}
	public void deleteClient(Client c) {
		clientrepo.delete(c);
	}
	
	public List<Account> addAccount(long id) {
		if(!clientrepo.findById(id).isPresent())System.out.println("le client nexiste pas");
	     
		 Client client=clientrepo.findById(id).get();
	     Account account=new Account();
	     account.setUid(UUID.randomUUID().toString());
	     account.setBalance(0);
	     client.getAccountList().add(account);
	     clientrepo.save(client);
	     return accountrepo.findAll();
	}
	public List<Account> deleteAccount(String uuid,long id){
		
		Account account=checkAccount(uuid);
		Client client=checkClient(id);
		client.getAccountList().remove(account);
		clientrepo.save(client);
		return accountrepo.findAll();
	}
	public Account findAccountByUid(String uid) {
		return checkAccount(uid);
	}
	
	public void deposit(String uid,double amount) {
		Account account=checkAccount(uid);
		
		Transaction transaction=new Transaction();
		transaction.setAmount(amount);
		transaction.setOperationType("Deposit");
		transaction.setDate(Instant.now());
		account.setBalance(account.getBalance()+amount);
		account.getTransList().add(transaction);
		accountrepo.save(account);
		
	}
	public void withdraw(String uid,double amount) {
		Account account=checkAccount(uid);
		if(account.getBalance() < amount) System.out.println("sdfsfdf");
		Transaction transaction=new Transaction();
		transaction.setAmount(amount);
		transaction.setOperationType("Withdraw");
		transaction.setDate(Instant.now());
		account.setBalance(account.getBalance()-amount);
		account.getTransList().add(transaction);
		accountrepo.save(account);
	}
	public double getBalanceAccount(String uid)
	{
		Account account=checkAccount(uid);
		return account.getBalance();
	}
	public List<Transaction> getAllTransaction(long id,String uid){
		Client client=checkClient(id);
		return client.getAccountList().stream().filter(p->p.getUid().equalsIgnoreCase(uid)).collect(Collectors.toList()).get(0).getTransList();
	}
	public Account checkAccount(String uuid ) {
		if(!accountrepo.findByUid(uuid).isPresent())return null;
		return accountrepo.findByUid(uuid).get();
	}
	public Client checkClient(long id) {
		if(!clientrepo.findById(id).isPresent())return null;
	    return clientrepo.findById(id).get();
	}
	
}
