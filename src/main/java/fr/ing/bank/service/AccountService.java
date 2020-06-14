package fr.ing.bank.service;

import fr.ing.bank.dao.AccountDao;
import fr.ing.bank.exception.ServiceException;
import fr.ing.bank.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountService {

    private static final double MINIMUM_DEPOSIT_AMOUNT = 0.01;

    private final AccountDao accountDao;

    @Autowired
    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public Account getAccount(String accountNumber) throws ServiceException {
        Account account = accountDao.getAccount(accountNumber);
        if (account == null) {
            log.error("account not found !");
            throw new ServiceException("account not found !");
        }
        return account;
    }

    public void deposit(String accountNumber, double amount) throws ServiceException {
        Account account = getAccount(accountNumber);
        if (amount <= MINIMUM_DEPOSIT_AMOUNT) {
            log.error("deposit money is not allowed when inferior or equal to 0.01 € !");
            throw new ServiceException("deposit money is not allowed when inferior or equal to 0.01 € !");
        }
        account.deposit(amount);
    }
}
