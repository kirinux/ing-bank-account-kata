package com.ing.kata.service;

import com.ing.kata.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Account save(Account account);
    List<Account> findAll();
    Optional<Account> findById(String id);
}
