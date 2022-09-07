package com.ing.kata.service;

import com.ing.kata.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Transaction save(Transaction transaction);
    Optional<Transaction> findById(String id);
    List<Transaction> findByAccount(String idClient);
}
