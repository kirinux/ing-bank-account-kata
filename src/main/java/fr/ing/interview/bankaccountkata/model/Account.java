package fr.ing.interview.bankaccountkata.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class Account implements Serializable{

    private int idAccount;
    private double balance;
    private Customer idClient;
    private List<Transaction> transactions;
    private double overdraft;
    private Date creationDate;


    public Account() {
    }

    public Account(int idAccount, long amount,int idClient) {
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
