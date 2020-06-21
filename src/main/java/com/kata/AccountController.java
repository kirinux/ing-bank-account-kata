package com.kata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kata.error.ObjectNotFoundException;
import com.kata.error.ObjectNotFoundException.ObjectType;
import com.kata.model.Account;
import com.kata.repo.CustomerRepository;
import com.kata.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
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
    	return accountService.depositAndReportBalance(accountId, amount);
    }
    
    @PutMapping("/withdraw/{accountId}")
    public double withdraw(@PathVariable long accountId, @RequestBody double amount) {
      return accountService.withdrawAndReportBalance(accountId, amount);
    }
    

}
