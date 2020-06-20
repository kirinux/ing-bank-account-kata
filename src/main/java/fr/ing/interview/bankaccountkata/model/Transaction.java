package fr.ing.interview.bankaccountkata.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


@Entity
public class Transaction implements Serializable{
    @Id
    @GeneratedValue
    private Long idTransaction;

    private Long operationNumber;
    private Date operationDate;
    private double amount;
    @ManyToOne
    @JoinColumn(name="ID_ACCOUNT")
    private Account account;
    private EnumTransactionType transactionType;

    public Transaction() {
    }

    public Transaction(Long operationNumber, LocalDate now, double amount, Account account) {
        this.operationNumber = operationNumber;
        this.operationDate = operationDate;
        this.amount = amount;
        this.account = account;
    }

    public Long getOperationNumber() {
        return operationNumber;
    }

    public void setOperationNumber(Long operationNumber) {
        this.operationNumber = operationNumber;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
