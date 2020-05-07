package fr.ing.interview.bankaccountkata.service;

import fr.ing.interview.bankaccountkata.dto.AccountDto;
import fr.ing.interview.bankaccountkata.dto.TransactionHistoryDto;
import fr.ing.interview.bankaccountkata.entities.Account;
import fr.ing.interview.bankaccountkata.entities.TransactionHistory;
import fr.ing.interview.bankaccountkata.repository.AccountRepository;
import fr.ing.interview.bankaccountkata.repository.TransactionHistoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author kso
 */
@Service
public class AccountService {

    private AccountRepository accountRepository;
    private TransactionHistoryRepository transactionHistoryRepository;
    private ModelMapper modelMapper;

    @Autowired
    public AccountService(AccountRepository accountRepository,
                          TransactionHistoryRepository transactionHistoryRepository,
                          ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.transactionHistoryRepository = transactionHistoryRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<AccountDto> getAccountDto(Integer id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.map(a -> modelMapper.map(a, AccountDto.class));
    }

    public Optional<Account> findById(Integer id) {
        return accountRepository.findById(id);
    }

    public AccountDto depositMoney(Account account, Double amount) {
        double balance = account.getBalance() + amount;
        account.setBalance(balance);
        accountRepository.save(account);
        TransactionHistory transactionHistory = new TransactionHistory(account.getId(), amount, LocalDateTime.now(), true);
        transactionHistoryRepository.save(transactionHistory);
        return modelMapper.map(account, AccountDto.class);
    }

    public AccountDto withdrawMoney(Account account, Double amount) {
        double balance = account.getBalance() - amount;
        account.setBalance(balance);
        accountRepository.save(account);
        TransactionHistory transactionHistory = new TransactionHistory(account.getId(), amount, LocalDateTime.now(), false);
        transactionHistoryRepository.save(transactionHistory);
        return modelMapper.map(account, AccountDto.class);
    }

    public List<TransactionHistoryDto> getTransactionsHistory(Integer id) {
        return transactionHistoryRepository.findByAccountId(id).stream()
                .map(t -> modelMapper.map(t, TransactionHistoryDto.class))
                .collect(Collectors.toList());
    }
}
