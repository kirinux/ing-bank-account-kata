package fr.ing.interview.controller;

import fr.ing.interview.dto.ErrorDto;
import fr.ing.interview.dto.TransactionDto;
import fr.ing.interview.dto.TransferRequestDto;
import fr.ing.interview.exception.BankAccountException;
import fr.ing.interview.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController()
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("deposit")
    public ResponseEntity<?> deposit(@RequestBody TransferRequestDto transferRequestDto) {
        try {
            accountService.deposit(transferRequestDto);
            return ResponseEntity.ok().build();
        } catch (BankAccountException e) {
            ErrorDto errorDto = ErrorDto.builder().errorMessage(e.getMessage()).build();
            return ResponseEntity.ok(errorDto);
        }
    }

    @PostMapping("withdraw")
    public ResponseEntity<?> withdraw(@RequestBody TransferRequestDto transferRequestDto) {
        try {
            accountService.withdraw(transferRequestDto);
            return ResponseEntity.ok().build();
        } catch (BankAccountException e) {
            ErrorDto errorDto = ErrorDto.builder().errorMessage(e.getMessage()).build();
            return ResponseEntity.ok(errorDto);
        }
    }

    @GetMapping("balance/{accountId}")
    public ResponseEntity<?> balance(@PathVariable("accountId") Long accountId) {
        try {
            BigDecimal balance = accountService.balance(accountId);
            return ResponseEntity.ok(balance);
        } catch (BankAccountException e) {
            ErrorDto errorDto = ErrorDto.builder().errorMessage(e.getMessage()).build();
            return ResponseEntity.ok(errorDto);
        }
    }

    @GetMapping("transactions/{accountId}")
    public ResponseEntity<?> transactions(@PathVariable("accountId") Long accountId) {
        try {
            List<TransactionDto> transactions = accountService.transactions(accountId);
            return ResponseEntity.ok(transactions);
        } catch (BankAccountException e) {
            ErrorDto errorDto = ErrorDto.builder().errorMessage(e.getMessage()).build();
            return ResponseEntity.ok(errorDto);
        }
    }

}
