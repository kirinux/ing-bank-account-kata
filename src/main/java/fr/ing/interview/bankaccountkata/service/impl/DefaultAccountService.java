package fr.ing.interview.bankaccountkata.service.impl;

import fr.ing.interview.bankaccountkata.exception.INGEntityNotFoundException;
import fr.ing.interview.bankaccountkata.model.BankAccountModel;
import fr.ing.interview.bankaccountkata.repository.AccountRepository;
import fr.ing.interview.bankaccountkata.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor

public class DefaultAccountService implements AccountService {
    @Autowired
    private final AccountRepository accountRepository;


    @Override
    public BankAccountModel getAccountBalanceByID(long accountID) {
            return accountRepository
                    .findById(accountID)
                    .orElseThrow(() -> {
                        throw new INGEntityNotFoundException("Account with id: " + accountID + " not found");
                    });
    }

    @Override
    public BankAccountModel deposit(Long accountId, BigDecimal amount) {
        BankAccountModel account = accountRepository.getById(accountId);
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
        return account;
    }

    @Override
    public BankAccountModel withdraw(Long accountId, BigDecimal amount) {
        BankAccountModel account = accountRepository.getById(accountId);
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
        return account;
    }

}
