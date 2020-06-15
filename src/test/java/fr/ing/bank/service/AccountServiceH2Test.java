package fr.ing.bank.service;

import fr.ing.bank.model.Account;
import fr.ing.bank.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ActiveProfiles("test")
@SpringBootTest
public class AccountServiceH2Test {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void should_deposit_amount_be_synchronized() throws InterruptedException {
        // Given
        ExecutorService service = Executors.newFixedThreadPool(3);
        double amount = 10.0;
        long accountId = 1L;
        long customerId = 1L;
        // When
        IntStream.range(0, 100).forEach(count -> service.submit(() -> accountService.deposit(customerId, accountId, amount)));
        service.awaitTermination(10000, TimeUnit.MILLISECONDS);
        // Then
        double expected = 10.0 * 100;
        Optional<Account> account = accountRepository.findById(accountId);
        assert account.isPresent();
        assertThat(account.get().getBalance(), is(equalTo(expected)));
    }

    @Test
    void should_withdraw_amount_be_synchronized() throws InterruptedException {
        // Given
        ExecutorService service = Executors.newFixedThreadPool(3);
        long customerId = 1L;
        long accountId = 2L;
        double amount = 1.0;
        // When
        IntStream.range(0, 100).forEach(count -> service.submit(() -> accountService.withdraw(customerId, accountId, amount)));
        service.awaitTermination(10000, TimeUnit.MILLISECONDS);
        // Then
        double expected = 100.0 - 1.0 * 100;
        Optional<Account> account = accountRepository.findById(accountId);
        assert account.isPresent();
        assertThat(account.get().getBalance(), is(equalTo(expected)));
    }
}
