package fr.ing.interview.bankaccountkata.service;

import fr.ing.interview.bankaccountkata.model.Account;

public interface AccountService {

    double deposit(double amount,Account account) throws Exception;
    long withdraw();
    long getBalance();
    void addTransactionHistory();
    void getTransactionList();

}
