package com.ing.kata.controller;

import com.ing.kata.model.Transaction;
import com.ing.kata.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public Transaction save(@RequestBody Transaction transaction) {
        return transactionService.save(transaction);
    }

    @GetMapping("/history/{id}")
    public List<Transaction> getHistory(@PathVariable String id) {
        return transactionService.findByAccount(id);
    }
}
