package fr.ing.interview.bankaccountkata.controller;

import fr.ing.interview.bankaccountkata.converters.BankAccount2BalanceDTOConverter;
import fr.ing.interview.bankaccountkata.exception.INGEntityNotFoundException;
import fr.ing.interview.bankaccountkata.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class AccountController {
    public static final String GET_BALANCE = "/balance";

    @Autowired
    AccountService accountService;

    @RequestMapping(value = GET_BALANCE + "/{accountID}", method = RequestMethod.GET)
    public ResponseEntity getBalanceForAccount
            (@PathVariable(value = "accountID") long accountID) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(BankAccount2BalanceDTOConverter.convert(accountService.getAccountBalanceByID(accountID)));
        } catch (INGEntityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

}
