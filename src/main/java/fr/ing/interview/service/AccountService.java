package fr.ing.interview.service;

import fr.ing.interview.dto.TransferRequestDto;
import fr.ing.interview.entity.Account;
import fr.ing.interview.exception.BankAccountException;
import fr.ing.interview.exception.DepositTooLowException;
import fr.ing.interview.exception.NonexistentAccountException;
import fr.ing.interview.exception.OverdraftForbiddenException;
import fr.ing.interview.persistence.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

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
        accountRepository.save(account);
    }

    public BigDecimal balance(Long accountId) throws BankAccountException {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isEmpty()) {
            throw new NonexistentAccountException();
        }

        return accountOptional.get().getBalance();
    }

}
