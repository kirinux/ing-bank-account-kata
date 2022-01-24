package fr.ing.interview.bankaccountkata.converters;

import fr.ing.interview.bankaccountkata.dto.AccountBalanceDTO;
import fr.ing.interview.bankaccountkata.model.BankAccountModel;

public class BankAccount2BalanceDTOConverter {
    static public AccountBalanceDTO convert(BankAccountModel bankAccountModel) {
        AccountBalanceDTO accountBalanceDTO = new AccountBalanceDTO();
        accountBalanceDTO.setId(bankAccountModel.getId());
        accountBalanceDTO.setBalance(bankAccountModel.getBalance());
        return accountBalanceDTO;
    }
}
