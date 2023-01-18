package ing.kata.BankAccount;

import ing.kata.BankAccount.Dtos.TransactionDto;
import ing.kata.BankAccount.Entities.Transaction;
import ing.kata.BankAccount.Enums.TransactionStatus;
import ing.kata.BankAccount.Enums.TransactionType;
import ing.kata.BankAccount.Exceptions.NotValidTransactionException;
import ing.kata.BankAccount.Repositories.TransactionRepository;
import ing.kata.BankAccount.Services.AccountService;
import ing.kata.BankAccount.Services.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTests {

    @Mock
    TransactionRepository transactionRepository;
    @Mock
    AccountService accountService;
    @InjectMocks
    TransactionService transactionService;


    @Test
    void transactionAccepted() {
        //GIVEN
        TransactionDto transactionDto =
                TransactionDto.builder()
                        .type(TransactionType.DEPOSIT)
                        .amount(15)
                        .build();
        Transaction transactionEntity =
                Transaction.builder()
                        .type(TransactionType.DEPOSIT)
                        .amount(15)
                        .build();
        Mockito.when(accountService.processTransaction(any(), anyLong())).thenReturn(transactionEntity);
        ArgumentCaptor<Transaction> argument = ArgumentCaptor.forClass(Transaction.class);


        //WHEN
        transactionService.createTransaction(transactionDto, 1);

        //THEN
        verify(transactionRepository).save(argument.capture());
        Assertions.assertEquals(TransactionStatus.ACCEPTED, argument.getValue().getStatus());
    }


    @Test
    void transactionRefused() {
        //GIVEN
        TransactionDto transactionDto =
                TransactionDto.builder()
                        .type(TransactionType.WITHDRAW)
                        .amount(1000)
                        .build();
        Mockito.when(accountService.processTransaction(any(), anyLong())).thenThrow(new NotValidTransactionException("Insufficient funds"));
        ArgumentCaptor<Transaction> argument = ArgumentCaptor.forClass(Transaction.class);

        //WHEN
        transactionService.createTransaction(transactionDto, 1);

        //THEN
        verify(transactionRepository).save(argument.capture());
        Assertions.assertEquals(TransactionStatus.REFUSED, argument.getValue().getStatus());
    }

}
