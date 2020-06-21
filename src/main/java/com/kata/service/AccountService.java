package com.kata.service;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kata.error.IlegalTransException;
import com.kata.error.ObjectNotFoundException;
import com.kata.error.ObjectNotFoundException.ObjectType;
import com.kata.model.Account;
import com.kata.model.ActionType;
import com.kata.model.Customer;
import com.kata.model.Transaction;
import com.kata.repo.AccountRepository;
import com.kata.repo.TransactionRepository;

@Service
public class AccountService {
	private static final Logger logger = LogManager.getLogger(AccountService.class);

	 @Autowired
	 private AccountRepository accountRepo;
	 
	 @Autowired
	 private TransactionRepository transactionRepo;
	 
	 public boolean isAccountExist(long accountId) {
		 return accountRepo.findById(accountId).isPresent();
	 }
	 
	 public double getBalance(long accountId) {
		return findAccountById(accountId).getBalance();
	 }
	 
	 public Account findAccountById(long id) throws IlegalTransException {
		 return accountRepo.findById(id).orElseThrow(()-> new ObjectNotFoundException(id, ObjectType.ACCOUNT));
	 }
	 
	 public List<Account> findAccountByCustomer(long id) {
		 return accountRepo.findAccountsByCustomer(id);
	 }
	 
	 public synchronized double depositAndReportBalance(long accountId, double amount) throws IlegalTransException {
			 Account account = findAccountById(accountId);
			 if (amount <= 0.01) {
				 logger.error("Ilegale deposite amount : " + amount);
					throw new IlegalTransException("Ilegale deposite amount, amount should be superior to 0.01");
				}
			 //account.add(amount);
			 account.setBalance(account.getBalance() + amount);
			 accountRepo.save(account);
			 Transaction transaction = new Transaction(account, ActionType.DEPOSIT, amount, LocalDateTime.now());
			 transactionRepo.save(transaction);
			 return account.getBalance();
	 }
	 
	 public synchronized double withdrawAndReportBalance(long accountId, double amount) throws IlegalTransException {
		 	Account account = findAccountById(accountId);
		 	if (amount <= 0) {
		 		logger.error("Withdraw amount : " + amount + " should not be negative");
				throw new IlegalTransException("withdraw amount should not be negative.");
			}
			if (amount > account.getBalance()) {
				logger.error("Ilegale withdraw amount : " + amount + "should not be superior than current balance.");
				throw new IlegalTransException("withdraw amount should not be superior to the balance.");
			}
			//account.withdraw(amount);
			 account.setBalance(account.getBalance() - amount);
			 accountRepo.save(account);
			 Transaction transaction = new Transaction(account, ActionType.WITHDRAW, amount, LocalDateTime.now());
			 transactionRepo.save(transaction);
			 return account.getBalance();
	 }
	 
//	public List<Transaction> findAllTransactionsByAccount(long accountId) throws IlegalTransException {
//		return transactionRepo.findTransByAccount(accountId);
//	}
	
}
