package fr.ing.interview.bankaccountkata.service.impl;

import fr.ing.interview.bankaccountkata.exception.MoneyAmountException;
import fr.ing.interview.bankaccountkata.exception.MoneyDepositNotPossibleException;
import fr.ing.interview.bankaccountkata.exception.MoneyWithdrawalNotPossibleException;
import fr.ing.interview.bankaccountkata.model.dto.AccountDTO;
import fr.ing.interview.bankaccountkata.model.dto.ClientDTO;
import fr.ing.interview.bankaccountkata.model.dto.TransactionDTO;
import fr.ing.interview.bankaccountkata.model.enums.TransactionTypeEnum;
import fr.ing.interview.bankaccountkata.service.AccountService;
import fr.ing.interview.bankaccountkata.service.ClientService;
import fr.ing.interview.bankaccountkata.service.TransactionService;
import fr.ing.interview.bankaccountkata.service.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final ClientService clientService;
    private final AccountService accountService;
    private final ClientMapper clientMapper;

    @Autowired
    public TransactionServiceImpl(ClientService clientService, AccountService accountService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.accountService = accountService;
        this.clientMapper = clientMapper;
    }

    @Override
    public List<TransactionDTO> getAllClientTransactionsByClientAndByAccountNumber(String clientId, String accountNumber) {
        ClientDTO clientDTO = clientMapper.toClientDTO(clientService.getClientIfExistThenThrow(clientId));
        AccountDTO accountDTO = accountService.findByAccountNumberFromAccountListThenThrow(clientDTO.getAccounts(), accountNumber);
        return accountDTO.getTransactions();
    }

    @Override
    public AccountDTO addTransaction(String clientId, String accountNumber, TransactionDTO transactionDTO) {
        ClientDTO clientDTO = clientMapper.toClientDTO(clientService.getClientIfExistThenThrow(clientId));
        AccountDTO accountDTO = accountService.findByAccountNumberFromAccountListThenThrow(clientDTO.getAccounts(), accountNumber);
        return treatTransaction(clientDTO, accountDTO, transactionDTO);
    }

    public AccountDTO treatTransaction(ClientDTO clientDTO, AccountDTO accountDTO, TransactionDTO transactionDTO) {

        transactionDTO.setTimestamp(LocalDateTime.now());

        if (transactionDTO.getValue().signum() < 0) {
            throw new MoneyAmountException();
        }

        if (transactionDTO.getTransactionType() == TransactionTypeEnum.DEPOSIT) {
            if (transactionDTO.getValue().compareTo(BigDecimal.valueOf(0.01)) > 0) {
                accountDTO.setBalance(accountDTO.getBalance().add(transactionDTO.getValue()));
            } else {
                throw new MoneyDepositNotPossibleException();
            }
        }

        if (transactionDTO.getTransactionType() == TransactionTypeEnum.WITHDRAWAL) {
            if (accountDTO.getBalance().subtract(transactionDTO.getValue()).compareTo(BigDecimal.valueOf(0)) >= 0) {
                accountDTO.setBalance(accountDTO.getBalance().subtract(transactionDTO.getValue()));
            } else {
                throw new MoneyWithdrawalNotPossibleException();
            }
        }

        accountDTO.getTransactions().add(transactionDTO);
        clientDTO.getAccounts().set(clientDTO.getAccounts().indexOf(accountDTO), accountDTO);
        clientService.update(clientDTO);
        return accountDTO;
    }
}
