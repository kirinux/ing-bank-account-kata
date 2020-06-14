package fr.ing.bank.service;

import fr.ing.bank.dao.AccountDao;
import fr.ing.bank.dao.TransactionDao;
import fr.ing.bank.exception.ServiceException;
import fr.ing.bank.model.Account;
import fr.ing.bank.model.Transaction;
import fr.ing.bank.model.TransactionType;
import fr.ing.bank.util.TimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AccountService {

    private static final double MINIMUM_DEPOSIT_AMOUNT = 0.01;

    private final AccountDao accountDao;

    private final TransactionDao transactionDao;

    private final TimeService timeService;

    @Autowired
    public AccountService(AccountDao accountDao, TransactionDao transactionDao, TimeService timeService) {
        this.accountDao = accountDao;
        this.transactionDao = transactionDao;
        this.timeService = timeService;
    }

    public void deposit(String accountNumber, double amount) throws ServiceException {
        Account account = getAccount(accountNumber);
        if (amount <= MINIMUM_DEPOSIT_AMOUNT) {
            log.error("deposit money is not allowed when inferior or equal to 0.01 € !");
            throw new ServiceException("deposit money is not allowed when inferior or equal to 0.01 € !");
        }
        Transaction transaction = new Transaction(accountNumber, TransactionType.DEPOSIT, amount, timeService.now());
        transactionDao.addTransaction(transaction);
        account.deposit(amount);
    }

    public void withdraw(String accountNumber, double amount) throws ServiceException {
        Account account = getAccount(accountNumber);
        if (amount > account.getBalance()) {
            log.error("not enough balance in account: {} to withdraw !", accountNumber);
            throw new ServiceException("not enough balance in account to withdraw !");
        }
        Transaction transaction = new Transaction(accountNumber, TransactionType.WITHDRAW, amount, timeService.now());
        transactionDao.addTransaction(transaction);
        account.withdraw(amount);
    }

    public double getBalance(String accountNumber) throws ServiceException {
        Account account = getAccount(accountNumber);
        return account.getBalance();
    }

    public List<Transaction> getTransactions(String accountNumber) throws ServiceException {
        Account account = getAccount(accountNumber);
        return transactionDao.getTransactions(account.getAccountNumber());
    }

    private Account getAccount(String accountNumber) throws ServiceException {
        Account account = accountDao.getAccount(accountNumber);
        if (account == null) {
            log.error("account not found !");
            throw new ServiceException("account not found !");
        }
        return account;
    }
}
