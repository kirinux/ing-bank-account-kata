package com.ing.kata.service.impl;

import com.ing.kata.model.Bank;
import com.ing.kata.repository.BankRepository;
import com.ing.kata.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;

    @Override
    public Bank save(Bank bank) {
        return this.bankRepository.save(bank);
    }

    @Override
    public Optional<Bank> findById(String id) {
        return this.bankRepository.findById(id);
    }

    @Override
    public List<Bank> findAll() {
        return this.bankRepository.findAll();
    }
}
