package fr.ing.interview.bankaccountkata.service;

import fr.ing.interview.bankaccountkata.model.Account;
import fr.ing.interview.bankaccountkata.model.Customer;
import fr.ing.interview.bankaccountkata.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;




class AccountServiceImplTest {

    @Autowired
    AccountService accountService;




    @BeforeTestClass
    public void init(){
        accountService = new AccountServiceImpl();
     }



    @Test
    void shouldUpdateAmountWhenDeposit() throws Exception {
        Account account = new Account(1, 100.0, null,new ArrayList<Transaction>(), 100.0, null);
        AccountService  accountServices = new AccountServiceImpl();
        accountServices.deposit(100,account);
        assertEquals(account.getBalance(),200.0 );
    }


    @Test
    void shouldRiseExceptionWhenDeposit() throws Exception {
        Account account = new Account(1, 100.0, null,new ArrayList<Transaction>(), 100.0, null);
        assertThatThrownBy(()->{ accountService.deposit(100,account);}).isInstanceOf(Exception.class);
    }

    @Test
    void shouldSaveTransactionWhenDeposit() throws Exception {
        Account account = new Account(1, 200.0, null,new ArrayList<>(), 100.0, null);
        AccountService  accountServices = new AccountServiceImpl();
        accountServices.withdraw(50.0,account);
        assertEquals(account.getTransactions().get(0).getAmount(),50.0 );

    }


    @Test
    void shouldRiseExceptionIfNullAccountWhenDepositOverdraft() throws Exception {

        assertThatThrownBy(()->{ accountService.withdraw(200,null);}).isInstanceOf(Exception.class);
    }


    @Test
    void shouldWithdrawMoneyFromAccount() {
        Account account = new Account(1, 100.0, null,new ArrayList<>(), 0.0, null);

        assertThatThrownBy(()->{ accountService.withdraw(100,account);}).isInstanceOf(Exception.class);

    }


    @Test
    void should_get_balance() throws Exception {
        AccountService  accountServices = new AccountServiceImpl();
        long customerId = 1L;
        Account account1 = new Account();
        account1.setIdAccount(1);
        account1.setBalance(20.0);
        Account account2 = new Account();
        account2.setBalance(30.0);
        account2.setIdAccount(2);
        List<Account> accounts = Arrays.asList(account1,account2);
        Customer customer = new Customer(1,"customer1@gmail.com",accounts);
        double balance = accountServices.getBalance(customer,1);
        assertEquals(balance, 20.0);
    }





}
