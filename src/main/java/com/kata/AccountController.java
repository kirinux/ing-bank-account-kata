package com.kata;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kata.error.IllegalTransException;
import com.kata.error.ObjectNotFoundException;
import com.kata.error.IllegalTransException.ErrorAmountType;
import com.kata.error.ObjectNotFoundException.ObjectType;
import com.kata.model.Account;
import com.kata.model.ActionType;
import com.kata.model.Transaction;
import com.kata.repo.CustomerRepository;
import com.kata.service.AccountService;
import com.kata.service.TransactionService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	private static final Logger logger = LogManager.getLogger(AccountController.class);
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private TransactionService transactionService;
	
	
	// Find
    @GetMapping("/customer/{id}")
	List<Account> findAccountsByCustomerId(@PathVariable long id) {
			return accountService.findAccountByCustomer(customerRepository.findById(id)
					.orElseThrow(() -> new ObjectNotFoundException(id, ObjectType.ACCOUNT)).getId());
		
	}	
	
	// Find
    @GetMapping("/{accountId}")
	Account findAccountById(@PathVariable long accountId) {
		return accountService.findAccountById(accountId).orElseThrow(()-> new ObjectNotFoundException(accountId, ObjectType.ACCOUNT));
	}
    
    // Find
    @GetMapping("/balance/{accountId}")
	double getBalanceByAccount(@PathVariable long accountId) {
		return accountService.getBalance(accountId);
	}

    @PutMapping("/deposit/{accountId}")
    public double deposit(@PathVariable long accountId, @RequestBody double amount) {
    	if (amount <= 0.01) {
			logger.error("Illegal deposite amount : " + amount);
			throw new IllegalTransException("Illegal deposite amount, amount should be superior to 0.01",
					ErrorAmountType.ILEGAL_AMOUNT);
		}
		Account account = accountService.findAccountById(accountId)
				.orElseThrow(() -> new ObjectNotFoundException(accountId, ObjectType.ACCOUNT));
		account.deposit(amount);
		accountService.save(account);
		Transaction transaction = new Transaction(account, ActionType.DEPOSIT, amount, LocalDateTime.now());
		transactionService.save(transaction);
		return account.getBalance();
    
    }
    
    @PutMapping("/withdraw/{accountId}")
    public double withdraw(@PathVariable long accountId, @RequestBody double amount) {
    	if (amount <= 0) {
			logger.error("Withdraw amount : " + amount + " should not be negative");
			throw new IllegalTransException("withdraw amount should not be negative.",
					ErrorAmountType.NEGATIVE_AMOUNT);
		}
		Account account = accountService.findAccountById(accountId)
				.orElseThrow(() -> new ObjectNotFoundException(accountId, ObjectType.ACCOUNT));
		if (amount > account.getBalance()) {
			logger.error("Illegale withdraw amount : " + amount + "should not be superior than current balance.");
			throw new IllegalTransException("withdraw amount should not be superior to the balance.",
					ErrorAmountType.OUT_OF_RANGE_AMOUNT);
		}
		account.withdraw(amount);
		accountService.save(account);
		Transaction transaction = new Transaction(account, ActionType.WITHDRAW, amount, LocalDateTime.now());
		transactionService.save(transaction);
		return account.getBalance();
    }
    

}
