package fr.ing.interview.bankaccountkata.service;

import fr.ing.interview.bankaccountkata.model.BankAccountModel;

import java.math.BigDecimal;

public interface AccountService {
    public BankAccountModel getAccountBalanceByID(long accountID);

    public BankAccountModel deposit(Long accountId, BigDecimal amount);

    public BankAccountModel withdraw(Long accountId, BigDecimal amount);
}
