package fr.ing.interview.service;

import fr.ing.interview.entity.Account;
import fr.ing.interview.persistence.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AdminService {

    private final AccountRepository accountRepository;

    public AdminService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Long createAccount() {
        Account account = Account.builder().balance(BigDecimal.ZERO).build();
        account = accountRepository.save(account);

        return account.getId();
    }

}
