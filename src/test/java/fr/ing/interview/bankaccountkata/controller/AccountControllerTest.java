package fr.ing.interview.bankaccountkata.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

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

import fr.ing.interview.bankaccountkata.dto.AccountDto;
import fr.ing.interview.bankaccountkata.entity.Account;
import fr.ing.interview.bankaccountkata.repository.AccountRepository;
import fr.ing.interview.bankaccountkata.service.AccountService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountControllerTest {

	@Mock
	private AccountService accountService;
	
	@Mock
	private AccountRepository accountRepository;
	

	
	@InjectMocks
	private AccountController accountControllerImpl;
	
	AccountDto accountDto;
	Account account;
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
	mockMvc = MockMvcBuilders.standaloneSetup(accountControllerImpl).build();

	accountDto = new AccountDto(1L, "codeCompte", 200 );
	account= new Account(1L, "codeCompte", 200);
	}
	
	
	@Test
	public final void test_Should_Return_200_OK_getAll() throws Exception {

		when(accountService.getAll()).thenReturn(Arrays.asList(accountDto));	
		mockMvc.perform(get("/account")
				)
				.andExpect(status().isOk());

	}
	
	
	@Test
	public final void test_Should_Return_200_OK_getAccountById() throws Exception {

		when(accountService.findById(Mockito.anyLong())).thenReturn(accountDto);	
		mockMvc.perform(get("/account/{id}",1L)
				)
				.andExpect(status().isOk());

	}
	
	
	@Test
	public final void test_Should_Return_200_OK_createAccount() throws Exception {

		when(accountService.create(Mockito.any(AccountDto.class))).thenReturn(accountDto);	
		mockMvc.perform(post("/account")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(asJsonString(accountDto)))
				.andExpect(status().isOk());

	}
	
	@Test
	public final void test_Should_Return_200_OK_updateAccount() throws Exception {

		when(accountService.create(Mockito.any(AccountDto.class))).thenReturn(accountDto);	
		mockMvc.perform(put("/account")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(asJsonString(accountDto)))
				.andExpect(status().isOk());

	}
	
	@Test
	public final void test_Should_Return_200_OK_deleteAccount() throws Exception {

		when(accountService.create(Mockito.any(AccountDto.class))).thenReturn(accountDto);	
		mockMvc.perform(delete("/account/{id}",1L)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(asJsonString(accountDto)))
				.andExpect(status().isOk());

	}
	
	
	@Test
	public final void test_Should_Return_200_OK_getAccountBalanceByClientId() throws Exception {

		when(accountService.getAccountBalanceByClientId(Mockito.anyLong())).thenReturn(accountDto.getBalance());	
		mockMvc.perform(get("/account/balance/{id}",1L)
				)
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
