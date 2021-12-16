package fr.ing.interview.bankaccountkata.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ing.interview.bankaccountkata.dto.AccountDto;
import fr.ing.interview.bankaccountkata.entity.Account;
import fr.ing.interview.bankaccountkata.exception.AccountNotFoundException;
import fr.ing.interview.bankaccountkata.mapper.AccountMapper;
import fr.ing.interview.bankaccountkata.repository.AccountRepository;

/**
 * AccountServiceImpl
 *
 * @author Abir ZEFZEF
 */
@Service
@Transactional
public class AccountService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountMapper accountMapper;

	
	public List<AccountDto> getAll() {

		LOGGER.debug("get all the accounts");
		return accountMapper.toListDto(accountRepository.findAll());
	}

	
	public AccountDto findById(Long id) {
		LOGGER.debug("get account: {}", id);
		return accountMapper.accountEntityToAccountDto(accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id)));
	}

	
	public AccountDto create(AccountDto accountDto) {
		LOGGER.debug("create account : {}", accountDto.getAccountNumber());

		return accountMapper.accountEntityToAccountDto(
				accountRepository.save(accountMapper.accountDtoToAccountEntity(accountDto)));
	}

	
	public AccountDto update(AccountDto accountDto) {
		LOGGER.debug("update account: {}", accountDto.getAccountNumber());

		accountRepository.findById(accountDto.getId()).orElseThrow(() -> new AccountNotFoundException(accountDto.getId()));
		Account account = accountMapper.accountDtoToAccountEntity(accountDto);
		return accountMapper.accountEntityToAccountDto(accountRepository.save(account));
	}

	
	public void delete(Long id) {
		LOGGER.debug("delete account : {}", id);

		Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
		accountRepository.delete(account);

	}


	public double getAccountBalanceByClientId(Long id) {
		LOGGER.debug("get balance of account : {}", id);
		Account account = accountRepository.findByClientId(id).orElseThrow(() -> new AccountNotFoundException(id));

		return account.getBalance();
	}

}
