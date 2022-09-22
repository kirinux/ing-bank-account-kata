package fr.ing.interview.bankaccountkata.service.mapper;

import fr.ing.interview.bankaccountkata.model.Transaction;
import fr.ing.interview.bankaccountkata.model.dto.TransactionDTO;

import java.util.List;

public interface TransactionMapper {
    TransactionDTO toTransactionDTO(Transaction transaction);
    Transaction fromTransactionDTO(TransactionDTO transactionDTO);
    List<TransactionDTO> toListTransactionDTO(List<Transaction> transactionList);
    List<Transaction> fromListTransactionDTO(List<TransactionDTO> transactionDTOList);
}
