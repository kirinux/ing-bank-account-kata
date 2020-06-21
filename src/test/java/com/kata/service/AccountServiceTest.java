package com.kata.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.kata.error.IlegalTransException;
import com.kata.error.IlegalTransException.ErrorAmountType;
import com.kata.model.Account;
import com.kata.model.ActionType;
import com.kata.model.Customer;
import com.kata.model.Transaction;
import com.kata.repo.AccountRepository;
import com.kata.repo.TransactionRepository;

@SpringBootTest
@ActiveProfiles("test")
public class AccountServiceTest {
	
	@Mock
    private AccountRepository accountRepo;
	
	@Mock
	private TransactionRepository transactionRepo;
	
    @InjectMocks 
    private AccountService accountService;
    
    @Captor
    private ArgumentCaptor<Transaction> captor;


    @BeforeEach
    void setMockOutput() {
    	Customer customer = new Customer("hugo");
    	customer.setId(1L);
    	Account account = new Account(customer);
    	account.setAccountId(1L);
    	List<Account> list = new ArrayList<>();
    	list.add(account);
        when(accountRepo.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepo.findById(2L)).thenReturn(Optional.empty());
        when(accountRepo.findAccountsByCustomer(1L)).thenReturn(list);
    }
    
    @Test
    void test_if_account_exist() {
    	assertEquals(true, accountService.isAccountExist(1L));
    }

    @Test
    void test_find_account_OK() {
        Assertions.assertThat(accountService.findAccountById(1L).get().getAccountId()).isEqualTo(1L);
    }
    
    @Test
    void test_find_account_not_found() {
    	
    	assertEquals(true, accountService.findAccountById(2L).isEmpty());
//    	ObjectNotFoundException exception= org.junit.jupiter.api.Assertions.assertThrows(ObjectNotFoundException.class, () -> {
//    		accountService.findAccountById(2L);
//        }); 
//    	org.junit.jupiter.api.Assertions.assertTrue(exception.getObjectType().equals(ObjectType.ACCOUNT) && exception.getId() == 2L);
    }
    
    @Test
    void test_deposit_ok() {
    	double amount = 10.0;
    	assertEquals(amount, accountService.depositAndReportBalance(1L, amount));
    	verify(transactionRepo, times(1)).save(captor.capture());
    	Transaction captured = captor.getValue();
    	assertEquals(1L, captured.getAccount().getAccountId());
    	assertEquals(ActionType.DEPOSIT, captured.getActionType());
    	assertEquals(amount, captured.getAmount());	
    }
    
    @Test
    void test_deposit_amount_ilegal_KO() {
    	double amount = -1;
    	IlegalTransException exception = org.junit.jupiter.api.Assertions.assertThrows(IlegalTransException.class, () -> {
    		accountService.depositAndReportBalance(1L, amount);
        });
     
        String expectedMessage = "Ilegale deposite amount, amount should be superior to 0.01";
        String actualMessage = exception.getMessage(); 
        assertEquals(ErrorAmountType.ILEGAL_AMOUNT, exception.getType());
        org.junit.jupiter.api.Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    void test_withdraw_ok() {
    	Customer customer = new Customer("Tim");
    	customer.setId(3L);
    	Account account = new Account(customer);
    	account.setAccountId(3L);
    	double balance = 60;
    	account.setBalance(balance);
    	when(accountRepo.findById(3L)).thenReturn(Optional.of(account));
    	double amount = 20;
    	assertEquals(balance-amount, accountService.withdrawAndReportBalance(3L, amount));
    	verify(transactionRepo, times(1)).save(captor.capture());
    	Transaction captured = captor.getValue();
    	assertEquals(3L, captured.getAccount().getAccountId());
    	assertEquals(ActionType.WITHDRAW, captured.getActionType());
    	assertEquals(amount, captured.getAmount());	
    }
    
    @Test
    void test_withdraw_KO_amount_negative() {
    	double amount = -20.0;
    	IlegalTransException exception= org.junit.jupiter.api.Assertions.assertThrows(IlegalTransException.class, () -> {
    		accountService.withdrawAndReportBalance(1L, amount);
    	  }); 
    	 String expectedMessage = "withdraw amount should not be negative.";
         String actualMessage = exception.getMessage();
         assertEquals(ErrorAmountType.NEGATIVE_AMOUNT, exception.getType());
         assertEquals(expectedMessage, actualMessage);
     }
    
    @Test
    void test_withdraw_KO_amount_out_of_range() {
    	double amount = 20.0;
    	IlegalTransException exception= org.junit.jupiter.api.Assertions.assertThrows(IlegalTransException.class, () -> {
    		accountService.withdrawAndReportBalance(1L, amount);
    	  }); 
    	 String expectedMessage = "withdraw amount should not be superior to the balance.";
         String actualMessage = exception.getMessage();
         assertEquals(ErrorAmountType.OUT_OF_RANGE_AMOUNT, exception.getType());
         assertEquals(expectedMessage, actualMessage);
     }

}
