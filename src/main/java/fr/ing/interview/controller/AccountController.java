package fr.ing.interview.controller;

import fr.ing.interview.dto.TransferRequestDto;
import fr.ing.interview.dto.TransferResponseDto;
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
    public ResponseEntity<TransferResponseDto> deposit(@RequestBody TransferRequestDto transferRequestDto) {
        return ResponseEntity.ok(accountService.deposit(transferRequestDto));
    }

}
