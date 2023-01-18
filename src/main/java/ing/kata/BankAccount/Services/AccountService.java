package ing.kata.BankAccount.Services;

import ing.kata.BankAccount.Dtos.AccountDto;
import ing.kata.BankAccount.Entities.Account;
import ing.kata.BankAccount.Entities.Customer;
import ing.kata.BankAccount.Entities.Transaction;
import ing.kata.BankAccount.Enums.TransactionType;
import ing.kata.BankAccount.Exceptions.AccountNotFoundException;
import ing.kata.BankAccount.Exceptions.NotValidTransactionException;
import ing.kata.BankAccount.Repositories.AccountRepository;
import ing.kata.BankAccount.Repositories.CustomerRepository;
import ing.kata.BankAccount.Repositories.TransactionRepository;
import ing.kata.BankAccount.mappers.BankAccountMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Value("${deposit.min.amount}")
    private Double minDepositAmount;

    public Transaction processTransaction(Transaction transaction, long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("account with id " + accountId + " not found"));
        transaction.setAccount(account);

        validateTransaction(transaction);

        var balanceUpdated = switch (transaction.getType()) {
            case DEPOSIT -> account.getBalance() + transaction.getAmount();
            case WITHDRAW -> account.getBalance() - transaction.getAmount();
        };

        account.setBalance(balanceUpdated);
        accountRepository.save(account);
        return transaction;
    }

    private void validateTransaction(Transaction transaction) {

        if (transaction.getType() == TransactionType.DEPOSIT && transaction.getAmount() < minDepositAmount)
            throw new NotValidTransactionException("Transaction amount less than " + minDepositAmount);

        if (transaction.getType() == TransactionType.WITHDRAW && Math.abs(transaction.getAccount().getBalance() - transaction.getAmount()) > transaction.getAccount().getOverdraft())
            throw new NotValidTransactionException("Insufficient funds");

    }


    @Transactional
    public AccountDto createAccount(AccountDto account, long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();

        Account accountToSave = BankAccountMapper.INSTANCE.toAccount(account);
        accountToSave.setCustomer(customer);

        return BankAccountMapper.INSTANCE.toAccountDto(accountRepository.save(accountToSave));
    }

    public List<AccountDto> getAccountsByCustomer(long customerId) {
        return accountRepository.findByCustomerId(customerId).stream().map(BankAccountMapper.INSTANCE::toAccountDto).collect(Collectors.toList());
    }
}
