package com.kata.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.kata.model.Account;
import com.kata.model.ActionType;
import com.kata.model.Customer;
import com.kata.model.Transaction;
import com.kata.repo.AccountRepository;
import com.kata.repo.CustomerRepository;
import com.kata.repo.TransactionRepository;


@DataJpaTest
@ActiveProfiles("test")
public class RepositoryTest {
		@Autowired
	    private TestEntityManager entityManager;

	    @Autowired
	    private AccountRepository repository;
	    
	    @Autowired
	    private CustomerRepository customerRepository;
	    
	    @Autowired
	    private TransactionRepository transactionRepository;
	    
	    @BeforeEach
	    void init() {
	    	entityManager.persist(new Customer("hugo"));
	    }

	    @Test
	    public void testFindAccountByCustomer() {
	    	//entityManager.persist(new Customer("hugo"));
	    	List<Customer> customers = customerRepository.findAll();
	    	Assertions.assertThat(1).isEqualTo(customers.size());
	    	Customer customer = customers.get(0);
	        entityManager.persist(new Account(customer));

	        List<Account> accounts = repository.findAccountsByCustomer(customer.getId());
	        Assertions.assertThat(1).isEqualTo(accounts.size());
	        Assertions.assertThat(accounts).extracting(Account:: getCustomer).containsOnly(customer);
	    }
	    
	    @Test
	    public void testFindTransByAccount() {
	    	List<Customer> customers = customerRepository.findAll();
	    	Customer customer = customers.get(0);
	    	entityManager.persist(new Account(customer));
	    	
	    	List<Account> accounts = repository.findAccountsByCustomer(customer.getId());
	        Assertions.assertThat(1).isEqualTo(accounts.size());
	        Account account = accounts.get(0);
	    	Transaction tran = new Transaction(account, ActionType.DEPOSIT, 3.0, LocalDateTime.now());
	    	entityManager.persist(tran);
	    	
	    	List<Transaction> transactions = transactionRepository.findTransByAccount(account.getAccountId());
	    	 Assertions.assertThat(1).isEqualTo(transactions.size());
		     Assertions.assertThat(transactions).extracting(Transaction :: getAccount).containsOnly(account);
	    }
	    
}
