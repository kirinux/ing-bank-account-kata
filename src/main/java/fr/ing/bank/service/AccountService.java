package fr.ing.bank.service;

import fr.ing.bank.dto.AccountDto;
import fr.ing.bank.dto.TransactionDto;
import fr.ing.bank.exception.ErrorCatalog;
import fr.ing.bank.exception.ServiceException;
import fr.ing.bank.mapper.AccountMapper;
import fr.ing.bank.mapper.TransactionMapper;
import fr.ing.bank.model.Account;
import fr.ing.bank.model.Transaction;
import fr.ing.bank.model.TransactionType;
import fr.ing.bank.repository.AccountRepository;
import fr.ing.bank.repository.TransactionRepository;
import fr.ing.bank.util.TimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Transactional
@Slf4j
@RequiredArgsConstructor
@Service
public class AccountService {

    private static final double MINIMUM_DEPOSIT_AMOUNT = 0.01;

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    private final TimeService timeService;

    private final AccountMapper accountMapper;

    private final TransactionMapper transactionMapper;

    public List<AccountDto> getAccounts(long customerId) {
        List<Account> accounts = accountRepository.findAllByCustomerId(customerId);
        return accounts
                .stream()
                .map(accountMapper::map)
                .collect(toList());
    }


    public void deposit(long customerId, long accountId, double amount) {
        Account account = getAccount(customerId, accountId);
        if (amount <= MINIMUM_DEPOSIT_AMOUNT) {
            log.error("deposit money is not allowed when inferior or equal to 0.01 € !");
            throw new ServiceException(ErrorCatalog.TRANSACTION_NOT_ALLOWED, "deposit money is not allowed when inferior or equal to 0.01 € !");
        }
        account.setBalance(account.getBalance() + amount);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setDate(timeService.now());
        transactionRepository.save(transaction);
    }

    public void withdraw(long customerId, long accountId, double amount) {
        Account account = getAccount(customerId, accountId);
        if (amount > account.getBalance()) {
            log.error("not enough balance in account: {} to withdraw !", account.getId());
            throw new ServiceException(ErrorCatalog.TRANSACTION_NOT_ALLOWED, "not enough balance in account to withdraw !");
        }
        account.setBalance(account.getBalance() - amount);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setDate(timeService.now());
        transactionRepository.save(transaction);
    }

    public double getBalance(long customerId) {
        List<Account> accounts = accountRepository.findAllByCustomerId(customerId);
        return accounts.stream()
                .map(Account::getBalance)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public List<TransactionDto> getTransactions(long customerId, long accountId) {
        List<Transaction> transactions = transactionRepository.findAllByAccountCustomerIdAndAccountId(customerId, accountId);
        return transactions.stream()
                .map(transactionMapper::map)
                .collect(toList());
    }

    private Account getAccount(long customerId, long accountId) {
        Account account = accountRepository.findByCustomerIdAndId(customerId, accountId);
        if (account == null) {
            log.error("account not found !");
            throw new ServiceException(ErrorCatalog.ACCOUNT_NOT_FOUND);
        }
        return account;
    }
}
