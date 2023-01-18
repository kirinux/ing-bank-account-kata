package ing.kata.BankAccount;

import ing.kata.BankAccount.Entities.Account;
import ing.kata.BankAccount.Entities.Transaction;
import ing.kata.BankAccount.Enums.TransactionType;
import ing.kata.BankAccount.Exceptions.NotValidTransactionException;
import ing.kata.BankAccount.Repositories.AccountRepository;
import ing.kata.BankAccount.Services.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTests {

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    AccountService accountService;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(accountService, "minDepositAmount", 0.01);
    }

    @Test
    void depositSuccess() {
        //GIVEN
        Transaction transaction =
                Transaction.builder()
                        .type(TransactionType.DEPOSIT)
                        .amount(15)
                        .build();
        Account account = Account.builder()
                .id(1L)
                .balance(70)
                .overdraft(100)
                .build();

        Mockito.when(accountRepository.findById(any())).thenReturn(Optional.of(account));
        Mockito.when(accountRepository.save(any())).thenReturn(account);

        //WHEN
        Transaction transactionResult = accountService.processTransaction(transaction, 1L);

        //THEN
        Assertions.assertNotNull(transactionResult);
        Assertions.assertNotNull(transactionResult.getAccount());
        Assertions.assertEquals(85, transactionResult.getAccount().getBalance());
    }


    @Test
    void DepositAmountLessThanMinimumThrowsException() {
        //GIVEN
        Transaction transaction =
                Transaction.builder()
                        .type(TransactionType.DEPOSIT)
                        .amount(0.004)
                        .build();
        Account account = Account.builder()
                .id(1L)
                .balance(70)
                .overdraft(100)
                .build();

        Mockito.when(accountRepository.findById(any())).thenReturn(Optional.of(account));

        //WHEN
        NotValidTransactionException thrown = Assertions.assertThrows(NotValidTransactionException.class, () -> {
            accountService.processTransaction(transaction, 1L);
        });


        //THEN
        Assertions.assertEquals("Transaction amount less than 0.01", thrown.getMessage());
    }


    @Test
    void withdrawSuccess() {
        //GIVEN
        Transaction transaction =
                Transaction.builder()
                        .type(TransactionType.WITHDRAW)
                        .amount(15)
                        .build();

        Account account = Account.builder()
                .id(1L)
                .balance(70)
                .overdraft(100)
                .build();

        Mockito.when(accountRepository.findById(any())).thenReturn(Optional.of(account));
        Mockito.when(accountRepository.save(any())).thenReturn(account);

        //WHEN
        Transaction transactionResult = accountService.processTransaction(transaction, 1L);

        //THEN
        Assertions.assertNotNull(transactionResult);
        Assertions.assertNotNull(transactionResult.getAccount());
        Assertions.assertEquals(55, transactionResult.getAccount().getBalance());
    }


    @Test
    void withdrawAmountLessThanBalancePlusOverdraftThrowsException() {
        //GIVEN
        Transaction transaction =
                Transaction.builder()
                        .type(TransactionType.WITHDRAW)
                        .amount(171)
                        .build();
        Account account = Account.builder()
                .id(1L)
                .balance(70)
                .overdraft(100)
                .build();

        Mockito.when(accountRepository.findById(any())).thenReturn(Optional.of(account));

        //WHEN
        NotValidTransactionException thrown = Assertions.assertThrows(NotValidTransactionException.class, () -> {
            Transaction transactionResult = accountService.processTransaction(transaction, 1L);
        });


        //THEN
        Assertions.assertEquals("Insufficient funds", thrown.getMessage());
    }

}
