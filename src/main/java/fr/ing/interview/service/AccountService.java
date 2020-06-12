package fr.ing.interview.service;

import fr.ing.interview.dto.TransactionDto;
import fr.ing.interview.dto.TransferRequestDto;
import fr.ing.interview.entity.Account;
import fr.ing.interview.entity.Transaction;
import fr.ing.interview.entity.TransactionType;
import fr.ing.interview.exception.BankAccountException;
import fr.ing.interview.exception.DepositTooLowException;
import fr.ing.interview.exception.NonexistentAccountException;
import fr.ing.interview.exception.OverdraftForbiddenException;
import fr.ing.interview.persistence.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private static final BigDecimal MIN_AMOUNT = BigDecimal.valueOf(0.01);

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void deposit(TransferRequestDto transferRequestDto) throws BankAccountException {
        Optional<Account> accountOptional = accountRepository.findById(transferRequestDto.getAccountId());
        if (accountOptional.isEmpty()) {
            throw new NonexistentAccountException();
        }
        if (MIN_AMOUNT.compareTo(transferRequestDto.getAmount()) >= 0) {
            throw new DepositTooLowException();
        }

        Account account = accountOptional.get();
        account.setBalance(account.getBalance().add(transferRequestDto.getAmount()));

        Transaction transaction = Transaction.builder()
                .account(account)
                .transactionType(TransactionType.DEPOSIT)
                .amount(transferRequestDto.getAmount())
                .date(new Date())
                .build();
        account.getTransactions().add(transaction);

        accountRepository.save(account);
    }

    public void withdraw(TransferRequestDto transferRequestDto) throws BankAccountException {
        Optional<Account> accountOptional = accountRepository.findById(transferRequestDto.getAccountId());
        if (accountOptional.isEmpty()) {
            throw new NonexistentAccountException();
        }

        Account account = accountOptional.get();
        BigDecimal newBalance = account.getBalance().subtract(transferRequestDto.getAmount());

        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new OverdraftForbiddenException();
        }

        account.setBalance(newBalance);

        Transaction transaction = Transaction.builder()
                .account(account)
                .transactionType(TransactionType.WITHDRAWAL)
                .amount(transferRequestDto.getAmount())
                .date(new Date())
                .build();
        account.getTransactions().add(transaction);

        accountRepository.save(account);
    }

    public BigDecimal balance(Long accountId) throws BankAccountException {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isEmpty()) {
            throw new NonexistentAccountException();
        }

        return accountOptional.get().getBalance();
    }

    public List<TransactionDto> transactions(Long accountId) throws BankAccountException {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isEmpty()) {
            throw new NonexistentAccountException();
        }

        List<Transaction> transactions = accountOptional.get().getTransactions();
        return transactions.stream().map(t ->
                TransactionDto.builder()
                        .amount(t.getAmount())
                        .date(t.getDate())
                        .transactionType(t.getTransactionType())
                        .build())
                .collect(Collectors.toList());
    }

}
