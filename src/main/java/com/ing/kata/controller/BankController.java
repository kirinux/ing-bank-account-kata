package com.ing.kata.controller;

import com.ing.kata.model.Bank;
import com.ing.kata.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bank")
public class BankController {

    private final BankService bankService;

    @PostMapping
    public Bank save(@RequestBody Bank bank) {
        return this.bankService.save(bank);
    }

    @GetMapping
    public List<Bank> getAll(){
        return this.bankService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Bank> getById(@PathVariable String id){
        return this.bankService.findById(id);
    }
}
