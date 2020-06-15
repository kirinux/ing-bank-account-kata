package fr.ing.bank.dto;

import fr.ing.bank.model.TransactionType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionDto {

    private long id;

    private long accountId;

    private TransactionType transactionType;

    private double amount;

    private LocalDateTime date;
}
