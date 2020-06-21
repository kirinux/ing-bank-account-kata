package fr.ing.interview.bankaccountkata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class Account implements Serializable{

    @Id
    @GeneratedValue
    private int idAccount;
    private double balance;
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<Transaction> transactions;
    private double overdraft;

    @CreationTimestamp
    private LocalDateTime creationDate;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="ID_CUSTOMER")
    private Customer customer;


    public Account() {
    }

    public Account(int idAccount, double balance, List<Transaction> transactions) {
        this.idAccount = idAccount;
        this.balance = balance;
        this.transactions = transactions;

    }

    public Account(int idAccount, long amount) {
        this.idAccount = idAccount;
        this.balance = amount;
    }

    public Account(int idAccount, double balance,  List<Transaction> transactions, double overdraft, LocalDateTime creationDate) {
        this.idAccount = idAccount;
        this.balance = balance;
        this.transactions = transactions;
        this.overdraft = overdraft;
        this.creationDate = creationDate;
    }




    public int getIdAccount() {
        return idAccount;
    }

    public double getBalance() {
        return balance;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public List<Transaction> getTransactions() {
        if(this.transactions == null) return new ArrayList<>();
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public double getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }

    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Account{" +
                "idAccount=" + idAccount +
                ", balance=" + balance +
                ", transactions=" + transactions +
                ", overdraft=" + overdraft +
                ", creationDate=" + creationDate +
                ", customer=" + customer +
                '}';
    }
}
