package fr.ing.interview.bankaccountkata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
public class Transaction implements Serializable{
    @Id
    @GeneratedValue
    private Long idTransaction;
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long operationNumber;
    @CreationTimestamp
    private LocalDateTime operationDate;
    private double amount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ID_ACCOUNT")
    private Account account;
    private String transactionType;

    public Transaction() {
    }

    public Transaction(String type,  double amount, Account account,LocalDateTime operationDate) {
        this.operationDate =  operationDate;
        this.transactionType = type;
        this.amount = amount;
        this.account = account;
    }
    public Transaction(String type,  double amount, Account account) {
        this.transactionType = type;
        this.amount = amount;
        this.account = account;
    }

    public Transaction(Long idTransaction,String type,  double amount, Account account,LocalDateTime operationDate) {
        this.operationDate =  operationDate;
        this.idTransaction =  idTransaction;
        this.transactionType = type;
        this.amount = amount;
        this.account = account;
    }




    public LocalDateTime getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(LocalDateTime operationDate) {
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

    public Long getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Long idTransaction) {
        this.idTransaction = idTransaction;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
