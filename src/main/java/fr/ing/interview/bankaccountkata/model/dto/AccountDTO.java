package fr.ing.interview.bankaccountkata.model.dto;

import fr.ing.interview.bankaccountkata.model.enums.BankEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class AccountDTO {

    private String accountNumber;
    private double balance;
    private BankEnum bank;
    private List<TransactionDTO> transactions;
}
