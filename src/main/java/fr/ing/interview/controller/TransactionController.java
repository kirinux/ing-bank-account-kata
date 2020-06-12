package fr.ing.interview.controller;

import fr.ing.interview.dto.ErrorDto;
import fr.ing.interview.exception.BankAccountException;
import fr.ing.interview.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController()
@RequestMapping("transaction")
public class TransactionController {

    private final AccountService accountService;

    public TransactionController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("history/{accountId}")
    public ResponseEntity<?> balance(@PathVariable("accountId") Long accountId) {
        try {
            BigDecimal balance = accountService.balance(accountId);
            return ResponseEntity.ok(balance);
        } catch (BankAccountException e) {
            ErrorDto errorDto = ErrorDto.builder().errorMessage(e.getMessage()).build();
            return ResponseEntity.ok(errorDto);
        }
    }

}
