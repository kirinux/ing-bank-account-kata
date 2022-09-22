package fr.ing.interview.bankaccountkata.controller;

import fr.ing.interview.bankaccountkata.model.dto.AccountDTO;
import fr.ing.interview.bankaccountkata.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{clientId}")
    public List<AccountDTO> getAllClientAccounts(@PathVariable String clientId) {
        return accountService.getAllClientAccounts(clientId);
    }

    @GetMapping("/{clientId}/{accountNumber}")
    public AccountDTO findByAccountNumberFromClientAccountList(@PathVariable String clientId, @PathVariable String accountNumber) {
        return accountService.findByAccountNumberFromAccountList(getAllClientAccounts(clientId), accountNumber);
    }

    @PostMapping("/{clientId}")
    public AccountDTO addAccount(@PathVariable String clientId, @RequestBody AccountDTO accountDTO) {
        return accountService.addAccount(clientId, accountDTO);
    }


    @PutMapping("/{clientId}")
    public AccountDTO updateAccount(@PathVariable String clientId, @RequestBody AccountDTO accountDTO) {
        return accountService.updateAccount(clientId, accountDTO);
    }


    @DeleteMapping("/{clientId}/{accountNumber}")
    public void deleteAccount(@PathVariable String clientId, @PathVariable String accountNumber) {
        accountService.deleteAccount(clientId, accountNumber);
    }
}
