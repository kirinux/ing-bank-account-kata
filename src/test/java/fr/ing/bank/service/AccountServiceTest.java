package fr.ing.bank.service;

import fr.ing.bank.dao.AccountDao;
import fr.ing.bank.dao.TransactionDao;
import fr.ing.bank.exception.ServiceException;
import fr.ing.bank.model.Account;
import fr.ing.bank.model.Transaction;
import fr.ing.bank.model.TransactionType;
import fr.ing.bank.util.TimeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountDao accountDao;

    @Mock
    private TransactionDao transactionDao;

    @Mock
    private TimeService timeService;

    @Captor
    private ArgumentCaptor<Transaction> transactionArgumentCaptor;

    @Test
    void should_deposit_money() throws ServiceException {
        // Given
        String accountNumber = "123456";
        double initialBalance = 100.0;
        double amount = 100.0;
        LocalDateTime now = LocalDateTime.now();
        Account account = new Account(accountNumber, initialBalance);
        when(accountDao.getAccount(accountNumber)).thenReturn(account);
        when(timeService.now()).thenReturn(now);
        // When
        accountService.deposit(accountNumber, amount);
        // Then
        double expected = initialBalance + amount;
        assertThat(account.getBalance(), is(equalTo(expected)));
        verify(transactionDao).addTransaction(transactionArgumentCaptor.capture());
        Transaction transaction = transactionArgumentCaptor.getValue();
        assertThat(transaction.getAccountNumber(), is(equalTo(accountNumber)));
        assertThat(transaction.getTransactionType(), is(equalTo(TransactionType.DEPOSIT)));
        assertThat(transaction.getAmount(), is(equalTo(amount)));
        assertThat(transaction.getDate(), is(equalTo(now)));
    }

    @Test
    void should_deposit_throws_exception_when_account_not_found() {
        // Given
        String accountNumber = "123456";
        double amount = 100.0;
        when(accountDao.getAccount(accountNumber)).thenReturn(null);
        // When
        ServiceException thrown = Assertions.assertThrows(ServiceException.class, () -> accountService.deposit(accountNumber, amount));
        // Then
        assertThat(thrown.getMessage(), is(equalTo("account not found !")));
    }

    @Test
    void should_deposit_throws_exception_when_low_amount() {
        // Given
        String accountNumber = "123456";
        double amount = 0.009;
        Account account = new Account(accountNumber);
        when(accountDao.getAccount(accountNumber)).thenReturn(account);
        // When
        ServiceException thrown = Assertions.assertThrows(ServiceException.class, () -> accountService.deposit(accountNumber, amount));
        // Then
        assertThat(thrown.getMessage(), is(equalTo("deposit money is not allowed when inferior or equal to 0.01 € !")));
    }

    @Test
    void should_withdraw_money() throws ServiceException {
        // Given
        String accountNumber = "123456";
        double initialBalance = 150.0;
        double amount = 100.0;
        LocalDateTime now = LocalDateTime.now();
        Account account = new Account(accountNumber, initialBalance);
        when(accountDao.getAccount(accountNumber)).thenReturn(account);
        when(timeService.now()).thenReturn(now);
        // When
        accountService.withdraw(accountNumber, amount);
        // Then
        double expected = initialBalance - amount;
        assertThat(account.getBalance(), is(equalTo(expected)));
        verify(transactionDao).addTransaction(transactionArgumentCaptor.capture());
        Transaction transaction = transactionArgumentCaptor.getValue();
        assertThat(transaction.getAccountNumber(), is(equalTo(accountNumber)));
        assertThat(transaction.getTransactionType(), is(equalTo(TransactionType.WITHDRAW)));
        assertThat(transaction.getAmount(), is(equalTo(amount)));
        assertThat(transaction.getDate(), is(equalTo(now)));
    }

    @Test
    void should_withdraw_throws_exception_when_account_not_found() {
        // Given
        String accountNumber = "123456";
        double amount = 100.0;
        when(accountDao.getAccount(accountNumber)).thenReturn(null);
        // When
        ServiceException thrown = Assertions.assertThrows(ServiceException.class, () -> accountService.withdraw(accountNumber, amount));
        // Then
        assertThat(thrown.getMessage(), is(equalTo("account not found !")));
    }

    @Test
    void should_withdraw_throws_exception_when_not_enough_balance() {
        // Given
        String accountNumber = "123456";
        double initialBalance = 50.0;
        double amount = 100.0;
        Account account = new Account(accountNumber, initialBalance);
        when(accountDao.getAccount(accountNumber)).thenReturn(account);
        // When
        ServiceException thrown = Assertions.assertThrows(ServiceException.class, () -> accountService.withdraw(accountNumber, amount));
        // Then
        assertThat(thrown.getMessage(), is(equalTo("not enough balance in account to withdraw !")));
    }

    @Test
    void should_get_balance() throws ServiceException {
        // Given
        String accountNumber = "123456";
        double initialBalance = 50.0;
        Account account = new Account(accountNumber, initialBalance);
        when(accountDao.getAccount(accountNumber)).thenReturn(account);
        // When
        double balance = accountService.getBalance(accountNumber);
        // Then
        assertThat(balance, is(equalTo(initialBalance)));
    }

    @Test
    void should_get_balance_throws_exception_when_account_not_found() {
        // Given
        String accountNumber = "123456";
        when(accountDao.getAccount(accountNumber)).thenReturn(null);
        // When
        ServiceException thrown = Assertions.assertThrows(ServiceException.class, () -> accountService.getBalance(accountNumber));
        // Then
        assertThat(thrown.getMessage(), is(equalTo("account not found !")));
    }

    @Test
    void should_get_transactions() throws ServiceException {
        // Given
        String accountNumber = "123456";
        Account account = new Account(accountNumber);
        Transaction transaction1 = new Transaction(accountNumber, TransactionType.DEPOSIT, 100.0, LocalDateTime.now());
        Transaction transaction2 = new Transaction(accountNumber, TransactionType.WITHDRAW, 50.0, LocalDateTime.now());
        List<Transaction> expectedTransactions = new ArrayList<>();
        expectedTransactions.add(transaction1);
        expectedTransactions.add(transaction2);
        when(accountDao.getAccount(accountNumber)).thenReturn(account);
        when(transactionDao.getTransactions(accountNumber)).thenReturn(expectedTransactions);
        // When
        List<Transaction> transactions = accountService.getTransactions(accountNumber);
        // Then
        assertThat(transactions, is(equalTo(expectedTransactions)));
    }

    @Test
    void should_get_transactions_throws_exception_when_account_not_found() {
        // Given
        String accountNumber = "123456";
        when(accountDao.getAccount(accountNumber)).thenReturn(null);
        // When
        ServiceException thrown = Assertions.assertThrows(ServiceException.class, () -> accountService.getTransactions(accountNumber));
        // Then
        assertThat(thrown.getMessage(), is(equalTo("account not found !")));
    }
}
