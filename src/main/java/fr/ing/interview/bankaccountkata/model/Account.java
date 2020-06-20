package fr.ing.interview.bankaccountkata.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class Account implements Serializable{

    @Id
    @GeneratedValue
    private int idAccount;

    private double balance;
    private Customer idClient;
    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;
    private double overdraft;
    private Date creationDate;

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

    public Account(int idAccount, long amount, int idClient) {
        this.idAccount = idAccount;
        this.balance = amount;
    }

    public Account(int idAccount, double balance, Customer idClient, List<Transaction> transactions, double overdraft, Date creationDate) {
        this.idAccount = idAccount;
        this.balance = balance;
        this.idClient = idClient;
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

    public Customer getIdClient() {
        return idClient;
    }

    public void setIdClient(Customer idClient) {
        this.idClient = idClient;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
