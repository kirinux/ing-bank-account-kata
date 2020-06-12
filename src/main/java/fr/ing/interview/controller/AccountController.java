package fr.ing.interview.controller;

import fr.ing.interview.dto.TransferRequestDto;
import fr.ing.interview.dto.TransferResponseDto;
import fr.ing.interview.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("withdraw")
    public ResponseEntity<TransferResponseDto> withdraw(@RequestBody TransferRequestDto transferRequestDto) {
        return ResponseEntity.ok(accountService.withdraw(transferRequestDto));
    }

}
