package ing.kata.BankAccount.Dtos;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDto {

    private long id;

    private String name;

    private List<AccountDto> accounts = List.of();

}
