package fr.ing.interview.bankaccountkata.rest;

import fr.ing.interview.bankaccountkata.dto.AccountDto;
import fr.ing.interview.bankaccountkata.dto.TransactionHistoryDto;
import fr.ing.interview.bankaccountkata.entities.Account;
import fr.ing.interview.bankaccountkata.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author kso
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> getAccount(@PathVariable Integer id) {
        Optional<AccountDto> account = accountService.getAccountDto(id);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/{id}/deposit-money")
    public ResponseEntity<AccountDto> depositMoney(@PathVariable Integer id,
                                                   @RequestParam Double amount) {
        Optional<Account> account = accountService.findById(id);
        if (!account.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        if (account.get().getBalance() < 0.01) {
            return ResponseEntity.notFound().build();
        }
        AccountDto accountDto = accountService.depositMoney(account.get(), amount);
        return ResponseEntity.ok(accountDto);
    }

    @PostMapping(value = "/{id}/withdraw-money")
    public ResponseEntity<AccountDto> withdrawMoney(@PathVariable Integer id,
                                                    @RequestParam Double amount) {
        Optional<Account> account = accountService.findById(id);
        if (!account.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        if (account.get().getBalance() < 0) {
            return ResponseEntity.badRequest().build();
        }
        AccountDto accountDto = accountService.withdrawMoney(account.get(), amount);
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping(value = "/{id}/transactions-history")
    public ResponseEntity<List<TransactionHistoryDto>> getTransactionsHistory(@PathVariable Integer id) {
        List<TransactionHistoryDto> transactionsHistory = accountService.getTransactionsHistory(id);
        return CollectionUtils.isEmpty(transactionsHistory) ? ResponseEntity.noContent().build() :
                ResponseEntity.ok(transactionsHistory);
    }
}
