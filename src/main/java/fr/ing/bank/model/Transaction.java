package fr.ing.bank.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Transaction {

    private String accountNumber;

    private TransactionType transactionType;

    private double amount;

    private LocalDateTime date;

    public Transaction(String accountNumber, TransactionType transactionType, double amount, LocalDateTime date) {
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.date = date;
    }
}
