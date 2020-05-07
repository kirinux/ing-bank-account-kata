package fr.ing.interview.bankaccountkata;

import fr.ing.interview.bankaccountkata.dto.AccountDto;
import fr.ing.interview.bankaccountkata.dto.TransactionHistoryDto;
import fr.ing.interview.bankaccountkata.entities.Account;
import fr.ing.interview.bankaccountkata.repository.TransactionHistoryRepository;
import fr.ing.interview.bankaccountkata.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BankAccountKataApplicationTests {

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;

    Account account;

    @BeforeEach
    void loadAccount() {
        Optional<Account> optional = accountService.findById(1);
        assertThat(optional).isPresent();
        account = optional.get();
    }

    @Test
    void deposit_money_test() {
        Double oldBalance = account.getBalance();
        Double amount = 500d;
        long countBeforeTransaction = getTransactionHistoryListSize();
        AccountDto accountDto = accountService.depositMoney(account, amount);
        assertThat(accountDto.getBalance()).isEqualTo(oldBalance + amount);
        assertThat(getTransactionHistoryListSize()).isEqualTo(++countBeforeTransaction);
    }

    @Test
    void withdraw_money_test() {
        Double oldBalance = account.getBalance();
        Double amount = 500d;
        long countBeforeTransaction = getTransactionHistoryListSize();
        AccountDto accountDto = accountService.withdrawMoney(account, amount);
        assertThat(accountDto.getBalance()).isEqualTo(oldBalance - amount);
        assertThat(getTransactionHistoryListSize()).isEqualTo(++countBeforeTransaction);
    }

    @Test
    void list_transactions_history_test() {
        List<TransactionHistoryDto> accountDto = accountService.getTransactionsHistory(1);
        assertThat(accountDto).isNotEmpty();
    }

    private int getTransactionHistoryListSize() {
        return transactionHistoryRepository.findByAccountId(account.getId()).size();
    }

}
