package fr.ing.interview.bankaccountkata.controller;

import fr.ing.interview.bankaccountkata.model.dto.AccountDTO;
import fr.ing.interview.bankaccountkata.model.dto.TransactionDTO;
import fr.ing.interview.bankaccountkata.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{clientId}/{accountNumber}")
    public List<TransactionDTO> getAllClientTransactionsByClientAndByAccountNumber(@PathVariable String clientId, @PathVariable String accountNumber) {
        return transactionService.getAllClientTransactionsByClientAndByAccountNumber(clientId, accountNumber);
    }

    @PutMapping("/{clientId}/{accountNumber}")
    public AccountDTO addTransaction(@PathVariable String clientId, @PathVariable String accountNumber, @RequestBody TransactionDTO transactionDTO) {
        return transactionService.addTransaction(clientId, accountNumber, transactionDTO);
    }

}
