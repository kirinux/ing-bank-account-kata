package fr.ing.interview.bankaccountkata.controller;

import fr.ing.interview.bankaccountkata.converters.BankAccount2BalanceDTOConverter;
import fr.ing.interview.bankaccountkata.converters.Operation2OperationReponseDTOConverter;
import fr.ing.interview.bankaccountkata.dto.OperationRequestDTO;
import fr.ing.interview.bankaccountkata.exception.INGEntityNotFoundException;
import fr.ing.interview.bankaccountkata.exception.IllegalOperationException;
import fr.ing.interview.bankaccountkata.service.AccountService;
import fr.ing.interview.bankaccountkata.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class OperationController {
    public static final String POST_DEPOSIT = "/deposit";
    public static final String POST_WITHDRAW = "/withdraw";
    public static final String GET_OPERATIONS = "/operations";

    @Autowired
    OperationService operationService;

    @RequestMapping(value = POST_DEPOSIT, method = RequestMethod.POST)
    public ResponseEntity deposit
            (@RequestBody OperationRequestDTO depositRequestDTO) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Operation2OperationReponseDTOConverter.convert(operationService.deposit(depositRequestDTO)));
        } catch (IllegalOperationException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }
    }

    @RequestMapping(value = POST_WITHDRAW, method = RequestMethod.POST)
    public ResponseEntity withdraw
            (@RequestBody OperationRequestDTO depositRequestDTO) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Operation2OperationReponseDTOConverter.convert(operationService.withdraw(depositRequestDTO)));
        } catch (IllegalOperationException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }
    }

    @RequestMapping(value = GET_OPERATIONS + "/{accountID}", method = RequestMethod.GET)
    public ResponseEntity getOperationsHistoryForAccount
            (@PathVariable(value = "accountID") long accountID) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(operationService.getOperationsHistoryForAccount(accountID).stream().map(account -> Operation2OperationReponseDTOConverter.convert(account)));
        } catch (IllegalOperationException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }
    }
}
