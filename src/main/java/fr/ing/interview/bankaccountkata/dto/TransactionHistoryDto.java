package fr.ing.interview.bankaccountkata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author kso
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionHistoryDto {

    private int id;

    private int accountId;

    private Double amount;

    private LocalDateTime date;

    private boolean isDeposit;

}
