package fr.ing.bank.controller;

import fr.ing.bank.dto.AccountDto;
import fr.ing.bank.dto.TransactionDto;
import fr.ing.bank.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "API for bank account kata.")
@RequiredArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;

    @ApiOperation(value = "Get accounts")
    @GetMapping(value = "/accounts")
    public List<AccountDto> getAccounts() {
        long customerId = 1L;
        return accountService.getAccounts(customerId);
    }

    @ApiOperation(value = "Deposit money")
    @PostMapping(value = "/accounts/{accountId}/deposit")
    public void deposit(@PathVariable long accountId, @RequestBody double amount) {
        long customerId = 1L;
        accountService.deposit(customerId, accountId, amount);
    }

    @ApiOperation(value = "Withdraw money")
    @PostMapping(value = "/accounts/{accountId}/withdraw")
    public void withdraw(@PathVariable long accountId, @RequestBody double amount) {
        long customerId = 1L;
        accountService.withdraw(customerId, accountId, amount);
    }

    @ApiOperation(value = "Get global balance")
    @GetMapping(value = "/accounts/balance")
    public double getBalance() {
        long customerId = 1L;
        return accountService.getBalance(customerId);
    }

    @ApiOperation(value = "Get account's transactions")
    @GetMapping(value = "/accounts/{accountId}/transactions")
    public List<TransactionDto> getTransactions(@PathVariable long accountId) {
        long customerId = 1L;
        return accountService.getTransactions(customerId, accountId);
    }
}
