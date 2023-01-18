package ing.kata.BankAccount.Dtos;

import ing.kata.BankAccount.Enums.TransactionStatus;
import ing.kata.BankAccount.Enums.TransactionType;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
@Builder
public class TransactionDto {
    private long id;
    @NotNull
    private double amount;
    @NotNull
    private TransactionType type;
    private TransactionStatus status;
    private AccountDto account;

}
