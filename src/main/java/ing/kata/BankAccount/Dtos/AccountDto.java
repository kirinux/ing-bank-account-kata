package ing.kata.BankAccount.Dtos;

import ing.kata.BankAccount.Enums.Currency;
import lombok.Data;

import java.util.Set;


@Data
public class AccountDto {
    private long id;
    private double balance;
    private Currency currency = Currency.EUR;
    private double overdraft;
    private Set<TransactionDto> transactions = Set.of();

}
