package fr.ing.bank.mapper;

import fr.ing.bank.dto.TransactionDto;
import fr.ing.bank.model.Account;
import fr.ing.bank.model.Transaction;
import fr.ing.bank.model.TransactionType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class TransactionMapperTest {

    private TransactionMapper transactionMapper = new TransactionMapper();

    @Test
    void should_map_transaction() {
        // Given
        Account account = new Account();
        LocalDateTime now = LocalDateTime.now();
        account.setId(44L);
        Transaction transaction = new Transaction();
        transaction.setId(5L);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setAmount(455.0);
        transaction.setAccount(account);
        transaction.setDate(now);
        // When
        TransactionDto transactionDto = transactionMapper.map(transaction);
        // Then
        assertThat(transactionDto.getId(), is(equalTo(5L)));
        assertThat(transactionDto.getTransactionType(), is(equalTo(TransactionType.DEPOSIT)));
        assertThat(transactionDto.getAmount(), is(equalTo(455.0)));
        assertThat(transactionDto.getAccountId(), is(equalTo(44L)));
        assertThat(transactionDto.getDate(), is(equalTo(now)));


    }
}
