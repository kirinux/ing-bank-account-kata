package fr.ing.interview.bankaccountkata.model.dto;

import fr.ing.interview.bankaccountkata.model.enums.BankEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private String accountNumber;
    private BigDecimal balance;
    private BankEnum bank;
    private List<TransactionDTO> transactions;
}
