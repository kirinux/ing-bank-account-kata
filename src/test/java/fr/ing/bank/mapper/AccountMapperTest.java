package fr.ing.bank.mapper;

import fr.ing.bank.dto.AccountDto;
import fr.ing.bank.model.Account;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class AccountMapperTest {

    private AccountMapper accountMapper = new AccountMapper();

    @Test
    void should_map_account() {
        // Given
        Account account = new Account();
        account.setId(4L);
        account.setLabel("COMPTE_COURANT");
        account.setBalance(100.0);
        // When
        AccountDto accountDto = accountMapper.map(account);
        // Then
        assertThat(accountDto.getId(), is(equalTo(account.getId())));
        assertThat(accountDto.getLabel(), is(equalTo(account.getLabel())));
        assertThat(accountDto.getBalance(), is(equalTo(account.getBalance())));

    }
}
