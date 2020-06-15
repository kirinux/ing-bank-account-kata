package fr.ing.bank.mapper;

import fr.ing.bank.dto.AccountDto;
import fr.ing.bank.model.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper {

    public AccountDto map(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setLabel(account.getLabel());
        accountDto.setBalance(account.getBalance());
        return accountDto;
    }
}
