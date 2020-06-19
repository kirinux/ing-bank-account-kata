package fr.ing.interview.bankaccountkata.service;

import fr.ing.interview.bankaccountkata.model.Account;
import fr.ing.interview.bankaccountkata.model.Customer;
import fr.ing.interview.bankaccountkata.model.Transaction;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.event.annotation.BeforeTestClass;


import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AccountServiceImplTest {


    AccountService accountService;
    Account account;



    @BeforeTestClass
    public void init(){
        accountService = new AccountServiceImpl();
        account = new Account(1, 100.0, null,null, 100.0, null)   ;
    }



    @Test
    void shouldUpdateAmountWhenDeposit() throws Exception {
        accountService.deposit(100,account);
        assertEquals(account.getBalance(),account.getBalance() + 100 );
    }


    @Test
    void shouldRiseExceptionWhenDeposit() throws Exception {
        assertThatThrownBy(()->{ accountService.deposit(100,account);}).isInstanceOf(Exception.class);
    }


    @Test
    void withdraw() {
    }

    @Test
    void getBalance() {
    }

    @Test
    void addTransactionHistory() {
    }

    @Test
    void getTransactionList() {
    }

}
