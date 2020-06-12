package fr.ing.interview.controller;

import fr.ing.interview.dto.TransferDto;
import fr.ing.interview.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("deposit")
    public ResponseEntity<Boolean> deposit(@RequestBody TransferDto transferDto) {
        return ResponseEntity.ok(true);
    }

}
