package fr.ing.bank.dao;

import fr.ing.bank.model.Account;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class AccountDao {

    private Map<String, Account> accounts = Collections.singletonMap("123456", new Account("123456"));

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
}
