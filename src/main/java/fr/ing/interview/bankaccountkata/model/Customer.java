package fr.ing.interview.bankaccountkata.model;

import java.io.Serializable;
import java.util.List;

public class Customer implements Serializable{

    private int idustomer;
    private String firstName;
    private String lastName;
    private String adress;
    private String email;
    private Account account;
    private List<Account> accounts;

    public int getIdustomer() {
        return idustomer;
    }

    public void setIdustomer(int idustomer) {
        this.idustomer = idustomer;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
