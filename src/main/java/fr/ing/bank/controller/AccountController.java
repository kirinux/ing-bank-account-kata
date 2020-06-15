package fr.ing.bank.controller;

import fr.ing.bank.dto.AccountDto;
import fr.ing.bank.dto.TransactionDto;
import fr.ing.bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;

    @GetMapping(value = "/accounts")
    public List<AccountDto> getAccounts() {
        long customerId = 1L;
        return accountService.getAccounts(customerId);
    }

    @PostMapping(value = "/accounts/{accountId}/deposit")
    public void deposit(@PathVariable long accountId, @RequestBody double amount) {
        long customerId = 1L;
        accountService.deposit(customerId, accountId, amount);
    }

    @PostMapping(value = "/accounts/{accountId}/withdraw")
    public void withdraw(@PathVariable long accountId, @RequestBody double amount) {
        long customerId = 1L;
        accountService.withdraw(customerId, accountId, amount);
    }

    @GetMapping(value = "/accounts/balance")
    public double getBalance() {
        long customerId = 1L;
        return accountService.getBalance(customerId);
    }

    @GetMapping(value = "/accounts/{accountId}/transactions")
    public List<TransactionDto> getTransactions(@PathVariable long accountId) {
        long customerId = 1L;
        return accountService.getTransactions(customerId, accountId);
    }
}
