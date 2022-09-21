package fr.ing.interview.bankaccountkata.service;

import fr.ing.interview.bankaccountkata.model.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    List<AccountDTO> getAllClientAccounts(String id);
    AccountDTO findByAccountNumberFromAccountList(List<AccountDTO> accountDTOList, String accountNumber);
    AccountDTO findByAccountNumberFromAccountListThenThrow(List<AccountDTO> accountDTOList, String accountNumber);
    AccountDTO addAccount(String clientId, AccountDTO accountDTO);
    AccountDTO updateAccount(String clientId, AccountDTO accountDTO);
    void deleteAccount(String clientId, String accountNumber);
}
