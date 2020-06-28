package com.kata.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.kata.error.IllegalTransException;
import com.kata.error.ObjectNotFoundException;
import com.kata.error.ObjectNotFoundException.ObjectType;
import com.kata.model.Account;
import com.kata.repo.AccountRepository;
import com.kata.repo.TransactionRepository;

@Service
public class AccountService {
	
	
	 @Autowired
	 private AccountRepository accountRepo;
	 
	 @Autowired
	 private TransactionRepository transactionRepo;
	 
	 public boolean isAccountExist(long accountId) {
		 return accountRepo.findById(accountId).isPresent();
	 }
	 
	 public double getBalance(long accountId) {
		return findAccountById(accountId).orElseThrow(()-> new ObjectNotFoundException(accountId, ObjectType.ACCOUNT)).getBalance();
	 }
	 
	 @Cacheable(value="accounts", key="#accountId")
	 public Optional<Account> findAccountById(long accountId) throws IllegalTransException {
		 return accountRepo.findById(accountId);
	 }
	 
	 public List<Account> findAccountByCustomer(long customerId) {
		 return accountRepo.findAccountsByCustomer(customerId);
	 }
	 
	 public void save(Account account) {
		accountRepo.save(account);
	 }
}
