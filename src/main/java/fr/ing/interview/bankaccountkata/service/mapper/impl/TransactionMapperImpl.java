package fr.ing.interview.bankaccountkata.service.mapper.impl;

import fr.ing.interview.bankaccountkata.model.Transaction;
import fr.ing.interview.bankaccountkata.model.dto.TransactionDTO;
import fr.ing.interview.bankaccountkata.service.mapper.TransactionMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public TransactionDTO toTransactionDTO(Transaction transaction) {
        if (transaction == null) {
            return null;
        }

        TransactionDTO transactionDTO = new TransactionDTO();

        transactionDTO.setTransactionType(transaction.getTransactionType());
        transactionDTO.setTimestamp(transaction.getTimestamp());
        transactionDTO.setValue(transaction.getValue());
        return transactionDTO;
    }

    @Override
    public Transaction fromTransactionDTO(TransactionDTO transactionDTO) {
        if (transactionDTO == null) {
            return null;
        }

        Transaction transaction = new Transaction();

        transaction.setTransactionType(transactionDTO.getTransactionType());
        transaction.setTimestamp(transactionDTO.getTimestamp());
        transaction.setValue(transactionDTO.getValue());
        return transaction;
    }

    @Override
    public List<TransactionDTO> toListTransactionDTO(List<Transaction> transactionList) {
        if (transactionList == null) {
            return Collections.emptyList();
        }

        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        for (Transaction transaction : transactionList) {
            transactionDTOList.add(toTransactionDTO(transaction));
        }

        return transactionDTOList;
    }

    @Override
    public List<Transaction> fromListTransactionDTO(List<TransactionDTO> transactionDTOList) {
        if (transactionDTOList == null) {
            return Collections.emptyList();
        }

        List<Transaction> transactionList = new ArrayList<>();
        for (TransactionDTO transaction : transactionDTOList) {
            transactionList.add(fromTransactionDTO(transaction));
        }

        return transactionList;
    }
}
