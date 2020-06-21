package com.kata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kata.error.ObjectNotFoundException;
import com.kata.error.ObjectNotFoundException.ObjectType;
import com.kata.model.Transaction;
import com.kata.repo.TransactionRepository;

@Service
public class TransactionService {
	 @Autowired
	 private AccountService accountService;
	 
	 @Autowired
	 private TransactionRepository transactionRepository;
	 
	 
	 public List<Transaction> findAll(){
		 return transactionRepository.findAll();
	 }
	 
	 public List<Transaction> findTransByAccount(long accountId){
		 if (accountService.isAccountExist(accountId)) {
				return transactionRepository.findTransByAccount(accountId);
			}
			throw new ObjectNotFoundException(accountId, ObjectType.ACCOUNT);
	 }	 
}
