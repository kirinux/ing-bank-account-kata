package com.ing.kata.service.impl;

import com.ing.kata.model.Account;
import com.ing.kata.model.Transaction;
import com.ing.kata.model.TransactionType;
import com.ing.kata.repository.TransactionRepository;
import com.ing.kata.service.AccountService;
import com.ing.kata.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountService accountService;
    private final TransactionRepository transactionRepository;

    @Override
    public Transaction save(Transaction transaction) {
        if(TransactionType.DEPOSIT.equals(transaction.getTransactionType())
                && transaction.getAccount() != null
                && transaction.getAmount() != null) {
            Optional<Account> account = this.getAccount(transaction.getAccount().getId());
            if(account.isPresent()) {
                account.get().setBalance(account.get().getBalance().add(transaction.getAmount()));
                    transaction.setDate(new Date());
                    this.accountService.save(account.get());
                    return this.transactionRepository.save(transaction);
            }

        }
        if(TransactionType.WITHDRAW.equals(transaction.getTransactionType())
                && transaction.getAccount() != null
                && transaction.getAmount() != null) {
            Optional<Account> account = this.getAccount(transaction.getAccount().getId());
            if(account.isPresent()) {
                BigDecimal subtract = account.get().getBalance().subtract(transaction.getAmount());
                if(subtract.intValue()>0) {
                    account.get().setBalance(account.get().getBalance().subtract(transaction.getAmount()));
                    transaction.setDate(new Date());
                    this.accountService.save(account.get());
                    return this.transactionRepository.save(transaction);
                }
            }
        }

        return null;
    }

    @Override
    public Optional<Transaction> findById(String id) {
        return this.transactionRepository.findById(id);
    }

    @Override
    public List<Transaction> findByAccount(String idAccount) {
        return this.transactionRepository.findByAccount(idAccount);
    }

    private Optional<Account> getAccount(String id) {
        return accountService.findById(id);
    }

}
