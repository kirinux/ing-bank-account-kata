package fr.ing.bank.service;

import fr.ing.bank.dto.AccountDto;
import fr.ing.bank.dto.TransactionDto;
import fr.ing.bank.exception.ErrorCatalog;
import fr.ing.bank.exception.ServiceException;
import fr.ing.bank.mapper.AccountMapper;
import fr.ing.bank.mapper.TransactionMapper;
import fr.ing.bank.model.Account;
import fr.ing.bank.model.Transaction;
import fr.ing.bank.model.TransactionType;
import fr.ing.bank.repository.AccountRepository;
import fr.ing.bank.repository.TransactionRepository;
import fr.ing.bank.util.TimeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private TimeService timeService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountMapper accountMapper;

    @Mock
    private TransactionMapper transactionMapper;

    @Captor
    private ArgumentCaptor<Transaction> transactionArgumentCaptor;

    @Test
    void should_get_accounts() {
        // Given
        long customerId = 1L;
        Account account = new Account();
        AccountDto accountDto = new AccountDto();
        when(accountRepository.findAllByCustomerId(customerId)).thenReturn(Collections.singletonList(account));
        when(accountMapper.map(account)).thenReturn(accountDto);
        // When
        List<AccountDto> accounts = accountService.getAccounts(customerId);
        // Then
        assertThat(accounts, is(equalTo(Collections.singletonList(accountDto))));
    }

    @Test
    void should_deposit_money() {
        // Given
        long customerId = 1L;
        long accountId = 1L;
        double amount = 100.0;
        LocalDateTime now = LocalDateTime.now();
        Account account = new Account();
        when(accountRepository.findByCustomerIdAndId(customerId, accountId)).thenReturn(account);
        when(timeService.now()).thenReturn(now);
        // When
        accountService.deposit(customerId, accountId, amount);
        // Then
        assertThat(account.getBalance(), is(equalTo(amount)));
        verify(transactionRepository).save(transactionArgumentCaptor.capture());
        Transaction transaction = transactionArgumentCaptor.getValue();
        assertThat(transaction.getAccount(), is(equalTo(account)));
        assertThat(transaction.getTransactionType(), is(equalTo(TransactionType.DEPOSIT)));
        assertThat(transaction.getAmount(), is(equalTo(amount)));
        assertThat(transaction.getDate(), is(equalTo(now)));
    }

    @Test
    void should_deposit_money_throws_exception_when_account_not_found() {
        // Given
        long customerId = 1L;
        long accountId = 1L;
        double amount = 100.0;
        when(accountRepository.findByCustomerIdAndId(customerId, accountId)).thenReturn(null);
        // When
        ServiceException thrown = assertThrows(ServiceException.class, () -> accountService.deposit(customerId, accountId, amount));
        // Then
        assertThat(thrown.getMessage(), is(equalTo(ErrorCatalog.ACCOUNT_NOT_FOUND.getMessage())));

    }

    @Test
    void should_deposit_money_throws_exception_when_low_amount() {
        // Given
        long customerId = 1L;
        long accountId = 1L;
        double amount = 0.009;
        Account account = new Account();
        when(accountRepository.findByCustomerIdAndId(customerId, accountId)).thenReturn(account);
        // When
        ServiceException thrown = assertThrows(ServiceException.class, () -> accountService.deposit(customerId, accountId, amount));
        // Then
        assertThat(thrown.getMessage(), is(equalTo("deposit money is not allowed when inferior or equal to 0.01 € !")));
    }

    @Test
    void should_withdraw_money() {
        // Given
        long customerId = 1L;
        long accountId = 1L;
        double initialBalance = 100.0;
        double amount = 50.0;
        LocalDateTime now = LocalDateTime.now();
        Account account = new Account();
        account.setBalance(initialBalance);
        when(accountRepository.findByCustomerIdAndId(customerId, accountId)).thenReturn(account);
        when(timeService.now()).thenReturn(now);
        // When
        accountService.withdraw(customerId, accountId, amount);
        // Then
        double expected = initialBalance - amount;
        assertThat(account.getBalance(), is(equalTo(expected)));
        verify(transactionRepository).save(transactionArgumentCaptor.capture());
        Transaction transaction = transactionArgumentCaptor.getValue();
        assertThat(transaction.getAccount(), is(equalTo(account)));
        assertThat(transaction.getTransactionType(), is(equalTo(TransactionType.DEPOSIT)));
        assertThat(transaction.getAmount(), is(equalTo(amount)));
        assertThat(transaction.getDate(), is(equalTo(now)));
    }

    @Test
    void should_withdraw_money_throws_exception_when_account_not_found() {
        // Given
        long customerId = 1L;
        long accountId = 1L;
        double amount = 50.0;
        when(accountRepository.findByCustomerIdAndId(customerId, accountId)).thenReturn(null);
        // When
        ServiceException thrown = assertThrows(ServiceException.class, () -> accountService.withdraw(customerId, accountId, amount));
        // Then
        assertThat(thrown.getMessage(), is(equalTo(ErrorCatalog.ACCOUNT_NOT_FOUND.getMessage())));

    }

    @Test
    void should_withdraw_money_throws_exception_when_not_enough_balance_in_account() {
        // Given
        long customerId = 1L;
        long accountId = 1L;
        double initialBalance = 50.0;
        double amount = 100.0;
        Account account = new Account();
        account.setBalance(initialBalance);
        when(accountRepository.findByCustomerIdAndId(customerId, accountId)).thenReturn(account);
        // When
        ServiceException thrown = assertThrows(ServiceException.class, () -> accountService.withdraw(customerId, accountId, amount));
        // Then
        assertThat(thrown.getMessage(), is(equalTo("not enough balance in account to withdraw !")));
    }

    @Test
    void should_get_balance() {
        // Give
        long customerId = 1L;
        Account account1 = new Account();
        account1.setBalance(10.0);
        Account account2 = new Account();
        account2.setBalance(10.0);
        List<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);
        when(accountRepository.findAllByCustomerId(customerId)).thenReturn(accounts);
        // When
        double balance = accountService.getBalance(customerId);
        // Then
        assertThat(balance, is(equalTo(20.0)));
    }

    @Test
    void should_get_transactions() {
        // Given
        Transaction transaction = new Transaction();
        TransactionDto transactionDto = new TransactionDto();
        long customerId = 1L;
        long accountId = 1L;
        when(transactionRepository.findAllByAccountCustomerIdAndAccountId(customerId, accountId)).thenReturn(Collections.singletonList(transaction));
        when(transactionMapper.map(transaction)).thenReturn(transactionDto);
        // When
        List<TransactionDto> actual = accountService.getTransactions(customerId, accountId);
        // Then
        assertThat(actual, is(equalTo(Collections.singletonList(transactionDto))));
    }
}
