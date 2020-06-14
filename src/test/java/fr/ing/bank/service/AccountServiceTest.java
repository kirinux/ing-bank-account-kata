package fr.ing.bank.service;

import fr.ing.bank.dao.AccountDao;
import fr.ing.bank.exception.ServiceException;
import fr.ing.bank.model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountDao accountDao;

    @Test
    void should_deposit_money() throws ServiceException {
        // Given
        String accountNumber = "123456";
        double initialBalance = 100.0;
        double amount = 100.0;
        Account account = new Account(accountNumber, initialBalance);
        when(accountDao.getAccount(accountNumber)).thenReturn(account);
        // When
        accountService.deposit(accountNumber, amount);
        // Then
        double expected = initialBalance + amount;
        assertThat(account.getBalance(), is(equalTo(expected)));
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
        Account account = new Account(accountNumber, initialBalance);
        when(accountDao.getAccount(accountNumber)).thenReturn(account);
        // When
        accountService.withdraw(accountNumber, amount);
        // Then
        double expected = initialBalance - amount;
        assertThat(account.getBalance(), is(equalTo(expected)));
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
}
