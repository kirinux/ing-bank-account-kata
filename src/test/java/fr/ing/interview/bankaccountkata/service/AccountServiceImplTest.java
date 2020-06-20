package fr.ing.interview.bankaccountkata.service;

import fr.ing.interview.bankaccountkata.exception.OperationNotAllowedException;
import fr.ing.interview.bankaccountkata.model.Account;
import fr.ing.interview.bankaccountkata.model.Customer;
import fr.ing.interview.bankaccountkata.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@ActiveProfiles("test")
@SpringBootTest
class AccountServiceImplTest {

    @Autowired
    AccountServiceImpl accountServices;




    @BeforeTestClass
    public void init(){
     }



    @Test
    void shouldUpdateAmountWhenDeposit() throws Exception {
        Account account = new Account(1, 100.0, new ArrayList<Transaction>(), 100.0, null);
       // AccountService  accountServices = new AccountServiceImpl();
        accountServices.deposit(100,account);
        assertEquals(account.getBalance(),200.0 );
    }


    @Test
    void shouldRiseExceptionWhenDeposit() throws Exception {
        Account account = new Account(1, 100.0, new ArrayList<Transaction>(), 100.0, null);
        assertThatThrownBy(()->{ accountServices.deposit(0.001,account);}).isInstanceOf(OperationNotAllowedException.class);
    }

    @Test
    void shouldSaveTransactionWhenDeposit() throws Exception {
        Account account = new Account(1, 200.0, new ArrayList<>(), 100.0, null);
      //  AccountService  accountServices = new AccountServiceImpl();
        accountServices.deposit(50.0,account);
        assertEquals(account.getTransactions().get(0).getAmount(),50.0 );
    }


    @Test
    void shouldRiseExceptionIfNullAccountWhenDepositOverdraft() throws Exception {
        assertThatThrownBy(()->{ accountServices.withdraw(200,null);}).isInstanceOf(Exception.class);
    }


    @Test
    void shouldWithdrawMoneyFromAccount() throws Exception {
        Account account = new Account(1, 100.0,  new ArrayList<>(), 0.0, null);
        accountServices.withdraw(50.00,account);
        assertEquals(account.getBalance(),50.0 );

    }


    @Test
    void shouldGetBalance() throws Exception {
        AccountService  accountServices = new AccountServiceImpl();
        Account account1 = new Account();
        account1.setIdAccount(1);
        account1.setBalance(20.0);
        Account account2 = new Account();
        account2.setBalance(30.0);
        account2.setIdAccount(2);
        List<Account> accounts = Arrays.asList(account1,account2);
        Customer customer = new Customer(1,"customer1@gmail.com",accounts);
        assertEquals(account1.getBalance(), 20.0);
        assertNotEquals(account2.getBalance(), 20.0);
    }

    @Test
    void shouldGetTransactions() throws Exception {
       // AccountService  accountServices = new AccountServiceImpl();
        Account account = new Account(1, 100.0, new ArrayList<Transaction>());
        accountServices.deposit(100,account);
        accountServices.withdraw(80,account);
        accountServices.deposit(20,account);
        accountServices.withdraw(50,account);
        Account account2 = new Account();
        account2.setBalance(30.0);
        account2.setIdAccount(2);
        List<Account> accounts = Arrays.asList(account,account2);
        Customer customer = new Customer(1,"customer1@gmail.com",accounts);
        List<Transaction> transactions = accountServices.getTransactions(customer,1);
        assertEquals(transactions.size(), 4);
    }





}
