package fr.ing.interview.bankaccountkata.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import fr.ing.interview.bankaccountkata.dto.AccountDto;
import fr.ing.interview.bankaccountkata.entity.Account;
import fr.ing.interview.bankaccountkata.mapper.AccountMapper;
import fr.ing.interview.bankaccountkata.repository.AccountRepository;
import fr.ing.interview.bankaccountkata.service.AccountService;

@RunWith(SpringRunner.class)
public class AccountServiceTest {

	@Mock
	private AccountRepository accountRepository;
	
	@Mock
	AccountMapper accountMapper;
		
	@InjectMocks
	private AccountService accountService;
	
	AccountDto accountDto;
	Account account;
	List<Account> accounts;
	List<AccountDto> accountsDto;
	
	@Before
	public void setUp() throws Exception {
	accountDto = new AccountDto(1L, "codeCompte", 200);
	account= new Account(1L, "codeCompte", 200);
	
	accounts = new ArrayList<Account>();
	accounts.add(account);
	
	accountsDto = new ArrayList<AccountDto>();
	accountsDto.add(accountDto);
	}
	
	@Test
	public final void test_getAll() {

		
		when(accountRepository.findAll()).thenReturn(accounts);
		when(accountMapper.accountEntityToAccountDto(Mockito.any(Account.class))).thenReturn(accountDto);
		when(accountMapper.toListDto(accounts)).thenReturn(accountsDto);
		List<AccountDto> accountsDtoResult = accountService.getAll();
		assertEquals(accountsDtoResult, accountsDto);
	}
	
	@Test
	public final void test_findById() {
		
		when(accountRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(account));

		when(accountMapper.accountEntityToAccountDto(Mockito.any(Account.class))).thenReturn(accountDto);
		
		AccountDto accountResult = accountService.findById(accountDto.getId());
		
		assertEquals(accountResult, accountDto);

	}
	
	
	@Test
	public final void test_create() {

		when(accountMapper.accountDtoToAccountEntity(Mockito.any(AccountDto.class))).thenReturn(account);

		when(accountRepository.save(Mockito.any(Account.class))).thenReturn(account);
		when(accountMapper.accountEntityToAccountDto(Mockito.any(Account.class))).thenReturn(accountDto);

		AccountDto account = accountService.create(accountDto);
		
		assertNotNull(account);
	}
	
	@Test
	public final void test_update() {		
		when(accountRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(account));
		when(accountMapper.accountDtoToAccountEntity(Mockito.any(AccountDto.class))).thenReturn(account);
		when(accountRepository.save(Mockito.any(Account.class))).thenReturn(account);
		when(accountMapper.accountEntityToAccountDto(Mockito.any(Account.class))).thenReturn(accountDto);

		AccountDto account = accountService.update(accountDto);
		
		assertNotNull(account);
	}

	@Test
	public final void test_delete() {
		when(accountRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(account));

		accountService.delete(accountDto.getId());
		
		verify(accountRepository).delete(account);
		
	}
	
	@Test
	public final void test_getAccountBalanceByClientId() {
		
		when(accountRepository.findByClientId(Mockito.anyLong())).thenReturn(Optional.of(account));
		
		double balance = accountService.getAccountBalanceByClientId(account.getId());
		assertEquals(balance, account.getBalance());
	}
}
