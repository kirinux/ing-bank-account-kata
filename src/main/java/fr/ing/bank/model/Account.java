package fr.ing.bank.model;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Account {

    private String accountNumber;

    private double balance;

    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public Account(String accountNumber) {
        this(accountNumber, 0.0);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public synchronized void deposit(double amount) {
        this.balance += amount;
        log.info("depositing the amount : " + amount);
        log.info("updated balance :  " + this.balance);
    }

    public synchronized void withdraw(double amount) {
        this.balance -= amount;
        log.info("successfully withdraw amount : {} from account: {} ", amount, accountNumber);
        log.info("updated balance :  " + this.balance);
    }
}
