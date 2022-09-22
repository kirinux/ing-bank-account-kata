package fr.ing.interview.bankaccountkata.model;

import fr.ing.interview.bankaccountkata.model.enums.TransactionTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Transaction implements Serializable {
    private static final long serialVersionUID = -7687807168156280783L;

    private TransactionTypeEnum transactionType;
    private BigDecimal value;
    private LocalDateTime timestamp;
}
