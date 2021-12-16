package fr.ing.interview.bankaccountkata.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import fr.ing.interview.bankaccountkata.controller.TransactionController;
import fr.ing.interview.bankaccountkata.dto.TransactionDto;
import fr.ing.interview.bankaccountkata.entity.Transaction;
import fr.ing.interview.bankaccountkata.service.TransactionService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionControllerTest {

	@Mock
	private TransactionService transactionService;
	
	@InjectMocks
	private TransactionController transactionControllerImpl;
	
	TransactionDto transactionDto;
	Transaction transaction;
	List<Transaction> transactions;
	List<TransactionDto> transactionsDto;
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
	mockMvc = MockMvcBuilders.standaloneSetup(transactionControllerImpl).build();

	transactionDto = new TransactionDto(1L, "transactionlabel", 200d ,LocalDateTime.now() , 1L,1L ,"");
	transaction = new Transaction(1L, "transactionlabel", 200d, LocalDateTime.now(), "");
	
	transactions = new ArrayList<Transaction>();
	transactions.add(transaction);
	
	
	transactionsDto = new ArrayList<TransactionDto>();
	transactionsDto.add(transactionDto);
	}
	
	
	@Test
	public final void test_Should_Return_200_OK_getAll() throws Exception {

		when(transactionService.getAll()).thenReturn(Arrays.asList(transactionDto));	
		mockMvc.perform(get("/transaction")
				)
				.andExpect(status().isOk());

	}
	
	
	@Test
	public final void test_Should_Return_200_OK_getTransactionById() throws Exception {

		when(transactionService.findById(Mockito.anyLong())).thenReturn(transactionDto);	
		mockMvc.perform(get("/transaction/{id}",1L)
				)
				.andExpect(status().isOk());

	}
	
	
	@Test
	public final void test_Should_Return_200_OK_createTransaction() throws Exception {

		when(transactionService.create(Mockito.any(TransactionDto.class))).thenReturn(transactionDto);	
		mockMvc.perform(post("/transaction")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(asJsonString(transactionDto)))
				.andExpect(status().isOk());

	}
	
	@Test
	public final void test_Should_Return_200_OK_updateTransaction() throws Exception {

		when(transactionService.create(Mockito.any(TransactionDto.class))).thenReturn(transactionDto);	
		mockMvc.perform(put("/transaction")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(asJsonString(transactionDto)))
				.andExpect(status().isOk());

	}
	
	@Test
	public final void test_Should_Return_200_OK_deleteTransaction() throws Exception {

		when(transactionService.create(Mockito.any(TransactionDto.class))).thenReturn(transactionDto);	
		mockMvc.perform(delete("/transaction/{id}",1L)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(asJsonString(transactionDto)))
				.andExpect(status().isOk());

	}
	
	
	@Test
	public final void test_Should_Return_200_OK_getAllTransactionsByClientAndCompte() throws Exception {

		when(transactionService.getAllByAccountAndClient(Mockito.anyLong(), Mockito.anyLong())).thenReturn(transactionsDto);	
		
		mockMvc.perform(get("/transaction/historique/{accountId}/{clientId}",1L,1L)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(asJsonString(transactionDto)))
				.andExpect(status().isOk());

	}
	
	// Méthode pour transformer un objet en json
	public static String asJsonString(final Object obj) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			// Configuration qui permet de garder les dates localdate en format json
			// "yyyy-MM-ddT00:00:00:000"
			mapper.registerModule(new JavaTimeModule());
			mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
