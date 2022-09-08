package com.ing.kata.controller;

import com.ing.kata.model.Account;
import com.ing.kata.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public List<Account> getAll() {
        return this.accountService.findAll();
    }

    @PostMapping
    public Account save(@RequestBody Account account) {
        return this.accountService.save(account);
    }

    @GetMapping("/{id}")
    public Optional<Account> getById(@PathVariable String id) {
        return this.accountService.findById(id);
    }
}
