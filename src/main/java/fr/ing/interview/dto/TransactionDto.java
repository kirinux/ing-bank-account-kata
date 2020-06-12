package fr.ing.interview.dto;

import fr.ing.interview.entity.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class TransactionDto implements Serializable {

    private TransactionType transactionType;

    private BigDecimal amount;

    private Date date;

}
