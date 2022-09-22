package fr.ing.interview.bankaccountkata.model;

import fr.ing.interview.bankaccountkata.model.enums.BankEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class Account implements Serializable {
    private static final long serialVersionUID = -2022957122630173595L;

    private String accountNumber;
    private BigDecimal balance;
    private BankEnum bank;
    private List<Transaction> transactions;
}
