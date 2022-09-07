package com.ing.kata.service.impl;

import com.ing.kata.model.Account;
import com.ing.kata.repository.AccountRepository;
import com.ing.kata.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Account save(Account account) {
        if(account.getId() == null) {
            account.setBalance(new BigDecimal(0));
        }
        return this.accountRepository.save(account);
    }

    @Override
    public List<Account> findAll() {
        return this.accountRepository.findAll();
    }

    @Override
    public Optional<Account> findById(String id) {
        return this.accountRepository.findById(id);
    }
}
