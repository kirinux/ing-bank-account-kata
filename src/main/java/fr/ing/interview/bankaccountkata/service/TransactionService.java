package fr.ing.interview.bankaccountkata.service;

import fr.ing.interview.bankaccountkata.model.dto.AccountDTO;
import fr.ing.interview.bankaccountkata.model.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    List<TransactionDTO> getAllClientTransactionsByClientAndByAccountNumber(String clientId, String accountNumber);
    AccountDTO addTransaction(String clientId, String accountNumber, TransactionDTO transactionDTO);
}
