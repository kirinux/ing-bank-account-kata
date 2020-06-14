package fr.ing.bank.model;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class AccountTest {

    @Test
    void should_deposit_amount_be_synchronized() throws InterruptedException {
        // Given
        ExecutorService service = Executors.newFixedThreadPool(3);
        String accountNumber = "123456";
        double initialBalance = 150.0;
        double amount = 10.0;
        Account account = new Account(accountNumber, initialBalance);
        // When
        IntStream.range(0, 1000).forEach(count -> service.submit(() -> account.deposit(amount)));
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);
        // Then
        double expected = initialBalance + 10.0 * 1000;
        assertThat(account.getBalance(), is(equalTo(expected)));
    }

    @Test
    void should_withdraw_amount_be_synchronized() throws InterruptedException {
        // Given
        ExecutorService service = Executors.newFixedThreadPool(3);
        String accountNumber = "123456";
        double initialBalance = 15000.0;
        double amount = 10.0;
        Account account = new Account(accountNumber, initialBalance);
        // When
        IntStream.range(0, 1000).forEach(count -> service.submit(() -> account.withdraw(amount)));
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);
        // Then
        double expected = initialBalance - amount * 1000;
        assertThat(account.getBalance(), is(equalTo(expected)));
    }
}
