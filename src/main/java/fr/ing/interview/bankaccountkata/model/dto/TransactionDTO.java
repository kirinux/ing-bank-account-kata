package fr.ing.interview.bankaccountkata.model.dto;

import fr.ing.interview.bankaccountkata.model.enums.TransactionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private TransactionTypeEnum transactionType;
    private BigDecimal value;
    private LocalDateTime timestamp;
}
