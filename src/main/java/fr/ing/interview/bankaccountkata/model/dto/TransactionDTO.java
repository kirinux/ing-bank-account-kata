package fr.ing.interview.bankaccountkata.model.dto;

import fr.ing.interview.bankaccountkata.model.enums.TransactionTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
public class TransactionDTO {

    private TransactionTypeEnum transactionType;
    private double value;
    private LocalDateTime timestamp;
}
