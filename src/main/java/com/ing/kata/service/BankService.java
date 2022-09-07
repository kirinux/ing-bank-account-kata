package com.ing.kata.service;

import com.ing.kata.model.Bank;

import java.util.List;
import java.util.Optional;

public interface BankService {
    Bank save(Bank bank);
    Optional<Bank> findById(String id);
    List<Bank> findAll();
}
