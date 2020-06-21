package com.kata.service;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.kata.model.Account;
import com.kata.model.ActionType;
import com.kata.model.Customer;
import com.kata.model.Transaction;
import com.kata.repo.TransactionRepository;

@SpringBootTest
@ActiveProfiles("test")
public class TransactionServiceTest {
	
	@Mock
	private TransactionRepository transactionRepo;
	
	@Mock 
    private AccountService accountService;
    
    @InjectMocks 
    private TransactionService transactionService;
    
    @Test
    void test_find_transactions_by_account_OK() {
    	Transaction t1= new Transaction(new Account(new Customer("user1")), ActionType.DEPOSIT, 30, LocalDateTime.now());
    	Transaction t2= new Transaction(new Account(new Customer("user2")), ActionType.WITHDRAW, 10, LocalDateTime.now());
    	List<Transaction> transactions = new ArrayList<>();
    	transactions.add(t1);
    	transactions.add(t2);
    	when(accountService.isAccountExist(1L)).thenReturn(true);
    	when(transactionRepo.findTransByAccount(1L)).thenReturn(transactions);
        Assertions.assertThat(transactionService.findTransByAccount(1L)).hasSize(2).containsExactly(t1,t2);
    }
    
    

}
