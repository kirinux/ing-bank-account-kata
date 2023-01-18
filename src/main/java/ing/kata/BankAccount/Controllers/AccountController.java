package ing.kata.BankAccount.Controllers;

import ing.kata.BankAccount.Dtos.AccountDto;
import ing.kata.BankAccount.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("customers/{customerId}/accounts")
    ResponseEntity<?> createAccount(@Valid @RequestBody AccountDto account, @PathVariable long customerId) {
        return ResponseEntity
                .ok()
                .body(accountService.createAccount(account, customerId));
    }

    @GetMapping("customers/{customerId}/accounts")
    ResponseEntity<?> getAccounts(@PathVariable long customerId) {
        return ResponseEntity
                .ok()
                .body(accountService.getAccountsByCustomer(customerId));
    }
}
