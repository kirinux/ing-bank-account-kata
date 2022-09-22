package fr.ing.interview.bankaccountkata.service.mapper.impl;

import fr.ing.interview.bankaccountkata.model.Account;
import fr.ing.interview.bankaccountkata.model.dto.AccountDTO;
import fr.ing.interview.bankaccountkata.service.mapper.AccountMapper;
import fr.ing.interview.bankaccountkata.service.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AccountMapperImpl implements AccountMapper {

    private final TransactionMapper transactionMapper;

    @Autowired
    public AccountMapperImpl(TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    }

    @Override
    public AccountDTO toAccountDTO(Account account) {
        if (account == null) {
            return null;
        }

        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setAccountNumber(account.getAccountNumber());
        accountDTO.setBank(account.getBank());
        accountDTO.setBalance(account.getBalance());
        accountDTO.setTransactions(transactionMapper.toListTransactionDTO(account.getTransactions()));
        return accountDTO;
    }

    @Override
    public Account fromAccountDTO(AccountDTO accountDTO) {
        if (accountDTO == null) {
            return null;
        }

        Account account = new Account();

        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setBank(accountDTO.getBank());
        account.setBalance(accountDTO.getBalance());
        account.setTransactions(transactionMapper.fromListTransactionDTO(accountDTO.getTransactions()));
        return account;
    }

    @Override
    public List<AccountDTO> toListAccountDTO(List<Account> accountList) {
        if (accountList == null) {
            return Collections.emptyList();
        }

        List<AccountDTO> accountDTOList = new ArrayList<>();
        for (Account account : accountList) {
            accountDTOList.add(toAccountDTO(account));
        }

        return accountDTOList;
    }

    @Override
    public List<Account> fromListAccountDTO(List<AccountDTO> accountDTOList) {
        if (accountDTOList == null) {
            return Collections.emptyList();
        }

        List<Account> accountList = new ArrayList<>();
        for (AccountDTO accountDTO : accountDTOList) {
            accountList.add(fromAccountDTO(accountDTO));
        }

        return accountList;
    }
}
