package fr.ing.bank.mapper;

import fr.ing.bank.dto.TransactionDto;
import fr.ing.bank.model.Transaction;
import org.springframework.stereotype.Service;

@Service
public class TransactionMapper {

    public TransactionDto map(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        if (transaction.getAccount() != null) {
            transactionDto.setAccountId(transaction.getAccount().getId());
        }
        transactionDto.setTransactionType(transaction.getTransactionType());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setDate(transaction.getDate());
        return transactionDto;
    }
}
