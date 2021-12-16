package fr.ing.interview.bankaccountkata.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ing.interview.bankaccountkata.dto.TransactionDto;
import fr.ing.interview.bankaccountkata.entity.Account;
import fr.ing.interview.bankaccountkata.entity.Transaction;
import fr.ing.interview.bankaccountkata.exception.InsufficientBalanceEception;
import fr.ing.interview.bankaccountkata.exception.InvalidAmmountEception;
import fr.ing.interview.bankaccountkata.exception.InvalidTransactionTypeEception;
import fr.ing.interview.bankaccountkata.exception.TransactionNotFoundException;
import fr.ing.interview.bankaccountkata.mapper.TransactionMapper;
import fr.ing.interview.bankaccountkata.repository.AccountRepository;
import fr.ing.interview.bankaccountkata.repository.TransactionRepository;

/**
 * TransactionServiceImpl
 *
 * @author Abir ZEFZEF
 */
@Service
@Transactional
public class TransactionService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TransactionMapper transactionMapper;

	
	public List<TransactionDto> getAll() {

		LOGGER.info("recuperer tous les transactions");
		return transactionRepository.findAll().stream().map(transactionMapper::transactionEntityToTransactionDto)
				.collect(Collectors.toList());
	}

	
	public TransactionDto findById(Long id) {
		LOGGER.info("recuperer le transaction : {}", id);
		return transactionMapper.transactionEntityToTransactionDto(
				transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id)));
	}

	
	public TransactionDto create(TransactionDto transactionDto) {
		LOGGER.info("creer le transaction : {}", transactionDto.getLabel());

		Account account = accountRepository.findById(transactionDto.getIdAccount()).get();
		double balance = account.getBalance();

		if (transactionDto.getType().equals("diposit")) {
			if (transactionDto.getValue().compareTo(0.01) > 0) {
				account.setBalance(balance + transactionDto.getValue());
			} else {
				throw new InvalidAmmountEception();
			}
		} else if (transactionDto.getType().equals("withdraw")) {
			if (account.getBalance() - transactionDto.getValue() >= 0) {
				account.setBalance(balance - transactionDto.getValue());
			} else {
				throw new InsufficientBalanceEception();
			}
		} else {
			throw new InvalidTransactionTypeEception();
		}

		accountRepository.save(account);

		return transactionMapper.transactionEntityToTransactionDto(
				transactionRepository.save(transactionMapper.transactionDtoToTransactionEntity(transactionDto)));
	}

	
	public TransactionDto update(TransactionDto transactionDto) {
		LOGGER.info("mise a jour le transaction : {}", transactionDto.getLabel());

		transactionRepository.findById(transactionDto.getId())
				.orElseThrow(() -> new TransactionNotFoundException(transactionDto.getId()));
		Transaction transaction = transactionMapper.transactionDtoToTransactionEntity(transactionDto);
		return transactionMapper.transactionEntityToTransactionDto(transactionRepository.save(transaction));
	}

	
	public void delete(Long id) {
		LOGGER.info("suppression le transaction : {}", id);

		Transaction transaction = transactionRepository.findById(id)
				.orElseThrow(() -> new TransactionNotFoundException(id));
		transactionRepository.delete(transaction);

	}

	
	public List<TransactionDto> getAllByAccountAndClient(Long accountId, Long clientId) {

		LOGGER.info("recuperer tous les transactions");
		return transactionRepository.findAllByAccountAndClient(accountId, clientId).stream()
				.map(transactionMapper::transactionEntityToTransactionDto).collect(Collectors.toList());
	}
}
