package fr.ing.interview.bankaccountkata.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
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

import fr.ing.interview.bankaccountkata.dto.TransactionDto;
import fr.ing.interview.bankaccountkata.entity.Account;
import fr.ing.interview.bankaccountkata.entity.Transaction;
import fr.ing.interview.bankaccountkata.exception.InsufficientBalanceEception;
import fr.ing.interview.bankaccountkata.exception.InvalidAmmountEception;
import fr.ing.interview.bankaccountkata.exception.InvalidTransactionTypeEception;
import fr.ing.interview.bankaccountkata.mapper.TransactionMapper;
import fr.ing.interview.bankaccountkata.repository.AccountRepository;
import fr.ing.interview.bankaccountkata.repository.TransactionRepository;
import fr.ing.interview.bankaccountkata.service.TransactionService;

@RunWith(SpringRunner.class)
public class TransactionServiceTest {

	private static final long ID = 1L;

	@Mock
	private TransactionRepository transactionRepository;

	@Mock
	private TransactionMapper transactionMapper;
	
	@Mock
	private AccountRepository accountRepository;

	@InjectMocks
	private TransactionService transactionService;

	TransactionDto transactionDto;
	Transaction transaction;
	
	TransactionDto invalidTypeTransactionDto;
	Transaction invalidTypeTransaction;
	
	Transaction dipositTransaction;
	TransactionDto dipositTransactionDto;

	Transaction withdrawTransaction;
	TransactionDto withdrawTransactionDto;
	
	Transaction insufisatntBalanceTransaction;
	TransactionDto insufisatntBalanceTransactionDto;
	
	Transaction invalidAmmountTransaction;
	TransactionDto invalidAmmountTransactionDto;
	Account account;

	List<Transaction> transactions;
	List<TransactionDto> transactionsDto;

	@Before
	public void setUp() throws Exception {

		transactionDto = new TransactionDto(1L, "transactionlabel", 0.02d ,LocalDateTime.now() , 1L,1L ,"");
		transaction = new Transaction(1L, "transactionlabel", 0.02d, LocalDateTime.now(), "");
		
		dipositTransactionDto = new TransactionDto(1L, "transactionlabel", 200d ,LocalDateTime.now() , 1L,1L ,"diposit");
		dipositTransaction = new Transaction(1L, "transactionlabel", 200d, LocalDateTime.now(), "diposit");
		
		withdrawTransaction = new Transaction(1L, "transactionlabel", 200.15d, LocalDateTime.now(), "withdraw");
		withdrawTransactionDto = new TransactionDto(1L, "transactionlabel", 200.15d ,LocalDateTime.now() , 1L,1L ,"diposit");

		insufisatntBalanceTransaction = new Transaction(1L, "transactionlabel", 400d, LocalDateTime.now(), "withdraw");
		insufisatntBalanceTransactionDto = new TransactionDto(1L, "transactionlabel", 400d ,LocalDateTime.now() , 1L,1L ,"withdraw");
		
		invalidAmmountTransaction = new Transaction(1L, "transactionlabel", 0d, LocalDateTime.now(), "diposit");
		invalidAmmountTransactionDto = new TransactionDto(1L, "transactionlabel", 0d ,LocalDateTime.now() , 1L,1L ,"diposit");

		invalidTypeTransactionDto = new TransactionDto(1L, "transactionlabel", 0d ,LocalDateTime.now() , 1L,1L ,"invalid type");
		invalidTypeTransaction = new Transaction(1L, "transactionlabel", 0d, LocalDateTime.now(), "invalid type");
		
		account = new Account(1L, "accountNumber", 200.15d);
		
		transactions = new ArrayList<Transaction>();
		transactions.add(transaction);

		transactionsDto = new ArrayList<TransactionDto>();
		transactionsDto.add(transactionDto);
	}

	@Test
	public final void test_getAll() {

		when(transactionRepository.findAll()).thenReturn(transactions);
		when(transactionMapper.transactionEntityToTransactionDto(Mockito.any(Transaction.class)))
				.thenReturn(transactionDto);
		when(transactionMapper.toListDto(transactions)).thenReturn(transactionsDto);
		List<TransactionDto> transactionsDtoResult = transactionService.getAll();
		assertEquals(transactionsDtoResult, transactionsDto);
	}

	@Test
	public final void test_findById() {

		when(transactionRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(transaction));

		when(transactionMapper.transactionEntityToTransactionDto(Mockito.any(Transaction.class)))
				.thenReturn(transactionDto);

		TransactionDto transactionResult = transactionService.findById(transactionDto.getId());

		assertEquals(transactionResult, transactionDto);

	}

	@Test
	public final void test_create_diposit_transaction() {

		when(accountRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(account));
		
		when(transactionMapper.transactionDtoToTransactionEntity(Mockito.any(TransactionDto.class)))
				.thenReturn(dipositTransaction);

		when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(dipositTransaction);
		when(transactionMapper.transactionEntityToTransactionDto(Mockito.any(Transaction.class)))
				.thenReturn(dipositTransactionDto);

		TransactionDto transaction = transactionService.create(dipositTransactionDto);

		assertEquals(transaction, dipositTransactionDto);
	}
	
	@Test(expected = InvalidAmmountEception.class )
	public final void test_throw_invalidAmmountException() {

		when(accountRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(account));
		
		when(transactionMapper.transactionDtoToTransactionEntity(Mockito.any(TransactionDto.class)))
				.thenReturn(invalidAmmountTransaction);

		when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(invalidAmmountTransaction);
		when(transactionMapper.transactionEntityToTransactionDto(Mockito.any(Transaction.class)))
				.thenReturn(invalidAmmountTransactionDto);

		 transactionService.create(invalidAmmountTransactionDto);


	}
	
	

	
	
	@Test
	public final void test_create_withdraw_transaction() {

		when(accountRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(account));
		
		when(transactionMapper.transactionDtoToTransactionEntity(Mockito.any(TransactionDto.class)))
				.thenReturn(withdrawTransaction);

		when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(dipositTransaction);
		when(transactionMapper.transactionEntityToTransactionDto(Mockito.any(Transaction.class)))
				.thenReturn(dipositTransactionDto);

		TransactionDto transaction = transactionService.create(withdrawTransactionDto);

		assertEquals(transaction, dipositTransactionDto);
	}
	
	
	@Test(expected = InsufficientBalanceEception.class )
	public final void test_throw_insufisatntBalanceException() {

		when(accountRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(account));
		
		when(transactionMapper.transactionDtoToTransactionEntity(Mockito.any(TransactionDto.class)))
				.thenReturn(insufisatntBalanceTransaction);

		when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(insufisatntBalanceTransaction);
		when(transactionMapper.transactionEntityToTransactionDto(Mockito.any(Transaction.class)))
				.thenReturn(insufisatntBalanceTransactionDto);

		transactionService.create(insufisatntBalanceTransactionDto);

	}

	
	@Test(expected = InvalidTransactionTypeEception.class )
	public final void test_throw_invalidTransactionTypeEception() {

		when(accountRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(account));
		
		when(transactionMapper.transactionDtoToTransactionEntity(Mockito.any(TransactionDto.class)))
				.thenReturn(invalidTypeTransaction);

		when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(invalidTypeTransaction);
		when(transactionMapper.transactionEntityToTransactionDto(Mockito.any(Transaction.class)))
				.thenReturn(invalidTypeTransactionDto);

		transactionService.create(invalidTypeTransactionDto);

	}
	
	
	@Test
	public final void test_update() {
		when(transactionRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(transaction));
		when(transactionMapper.transactionDtoToTransactionEntity(Mockito.any(TransactionDto.class)))
				.thenReturn(transaction);
		when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(transaction);
		when(transactionMapper.transactionEntityToTransactionDto(Mockito.any(Transaction.class)))
				.thenReturn(transactionDto);

		TransactionDto transaction = transactionService.update(transactionDto);

		assertNotNull(transaction);
	}

	@Test
	public final void test_delete() {
		when(transactionRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(transaction));

		transactionService.delete(transactionDto.getId());

		verify(transactionRepository).delete(transaction);

	}

	@Test
	public final void test_getAllByAccountAndClient() {
		when(transactionMapper.transactionEntityToTransactionDto(Mockito.any(Transaction.class)))
				.thenReturn(transactionDto);

		when(transactionRepository.findAllByAccountAndClient(Mockito.any(Long.class), Mockito.any(Long.class)))
				.thenReturn(transactions);

		List<TransactionDto> transactionsDtoResult = transactionService.getAllByAccountAndClient(ID, ID);

		assertEquals(transactionsDtoResult, transactionsDto);

	}

}
