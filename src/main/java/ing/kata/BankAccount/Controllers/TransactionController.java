package ing.kata.BankAccount.Controllers;

import ing.kata.BankAccount.Dtos.TransactionDto;
import ing.kata.BankAccount.Exceptions.AccountNotFoundException;
import ing.kata.BankAccount.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/accounts/{accountId}/transactions")
    ResponseEntity<?> createTransaction(@Valid @RequestBody TransactionDto transaction, @PathVariable long accountId) {
        try {
            transaction = transactionService.createTransaction(transaction, accountId);
        } catch (AccountNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
        return ResponseEntity
                .ok()
                .body(transaction);
    }

    @GetMapping("accounts/{accountId}/transactions")
    ResponseEntity<?> getTransactionsByAccount(@PathVariable long accountId) {
        return ResponseEntity
                .ok()
                .body(transactionService.getTransactionsByAccount(accountId));
    }

    @GetMapping("customers/{customerId}/transactions")
    ResponseEntity<?> getTransactionsByCustomer(@PathVariable long customerId) {
        return ResponseEntity
                .ok()
                .body(transactionService.getTransactionsByCustomer(customerId));
    }
}
