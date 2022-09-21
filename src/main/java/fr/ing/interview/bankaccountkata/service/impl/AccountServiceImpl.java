package fr.ing.interview.bankaccountkata.service.impl;

import fr.ing.interview.bankaccountkata.exception.AccountCreationNotPossibleException;
import fr.ing.interview.bankaccountkata.exception.AccountNotFoundException;
import fr.ing.interview.bankaccountkata.model.Client;
import fr.ing.interview.bankaccountkata.model.dto.AccountDTO;
import fr.ing.interview.bankaccountkata.model.dto.ClientDTO;
import fr.ing.interview.bankaccountkata.service.AccountService;
import fr.ing.interview.bankaccountkata.service.ClientService;
import fr.ing.interview.bankaccountkata.service.mapper.AccountMapper;
import fr.ing.interview.bankaccountkata.service.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private final ClientService clientService;
    private final ClientMapper clientMapper;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountServiceImpl(ClientService clientService, ClientMapper clientMapper, AccountMapper accountMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
        this.accountMapper = accountMapper;
    }

    @Override
    public List<AccountDTO> getAllClientAccounts(String id) {
        Client client = clientService.getClientIfExistThenThrow(id);
        return accountMapper.toListAccountDTO(client.getAccounts());
    }

    @Override
    public AccountDTO findByAccountNumberFromAccountList(List<AccountDTO> accountDTOList, String accountNumber) {
        for (AccountDTO accountDTO : accountDTOList) {
            if (accountDTO.getAccountNumber().equals(accountNumber)) {
                return accountDTO;
            }
        }
        return null;
    }

    @Override
    public AccountDTO findByAccountNumberFromAccountListThenThrow(List<AccountDTO> accountDTOList, String accountNumber) {
        AccountDTO accountDTOFound = findByAccountNumberFromAccountList(accountDTOList, accountNumber);
        if (accountDTOFound != null) {
            return accountDTOFound;
        }
        throw new AccountNotFoundException(accountNumber);
    }

    @Override
    public AccountDTO addAccount(String clientId, AccountDTO accountDTO) {
        ClientDTO clientDTO = clientMapper.toClientDTO(clientService.getClientIfExistThenThrow(clientId));
        AccountDTO accountDTOFound = findByAccountNumberFromAccountList(clientDTO.getAccounts(), accountDTO.getAccountNumber());
        if (accountDTOFound == null) {
            clientDTO.getAccounts().add(accountDTO);
            clientService.update(clientDTO);
            return accountDTO;
        } else throw new AccountCreationNotPossibleException(accountDTO.getAccountNumber());
    }

    @Override
    public AccountDTO updateAccount(String clientId, AccountDTO accountDTO) {
        ClientDTO clientDTO = clientMapper.toClientDTO(clientService.getClientIfExistThenThrow(clientId));
        AccountDTO accountDTOFound = findByAccountNumberFromAccountList(clientDTO.getAccounts(), accountDTO.getAccountNumber());
        if (accountDTOFound != null) {
            clientDTO.getAccounts().set(clientDTO.getAccounts().indexOf(accountDTOFound), accountDTOFound);
            clientService.update(clientDTO);
            return accountDTO;
        } else throw new AccountNotFoundException(accountDTO.getAccountNumber());
    }

    @Override
    public void deleteAccount(String clientId, String accountNumber) {
        ClientDTO clientDTO = clientMapper.toClientDTO(clientService.getClientIfExistThenThrow(clientId));
        AccountDTO accountDTOFound = findByAccountNumberFromAccountList(clientDTO.getAccounts(), accountNumber);
        if (accountDTOFound != null) {
            clientDTO.getAccounts().remove(accountDTOFound);
            clientService.update(clientDTO);
        } else throw new AccountNotFoundException(accountNumber);
    }


}
