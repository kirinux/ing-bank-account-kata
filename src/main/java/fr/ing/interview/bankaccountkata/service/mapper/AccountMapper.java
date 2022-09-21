package fr.ing.interview.bankaccountkata.service.mapper;

import fr.ing.interview.bankaccountkata.model.Account;
import fr.ing.interview.bankaccountkata.model.dto.AccountDTO;

import java.util.List;

public interface AccountMapper {
    AccountDTO toAccountDTO(Account account);
    Account fromAccountDTO(AccountDTO accountDTO);
    List<AccountDTO> toListAccountDTO(List<Account> accountList);
    List<Account> fromListAccountDTO(List<AccountDTO> accountDTOList);
}
