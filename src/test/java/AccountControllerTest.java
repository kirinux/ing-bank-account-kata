import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.kata.AccountController;
import com.kata.StartKataApplication;
import com.kata.error.CustomGlobalExceptionHandler;
import com.kata.error.IllegalTransException;
import com.kata.error.IllegalTransException.ErrorAmountType;
import com.kata.model.Account;
import com.kata.model.ActionType;
import com.kata.model.Customer;
import com.kata.model.Transaction;
import com.kata.repo.CustomerRepository;
import com.kata.service.AccountService;
import com.kata.service.TransactionService;

@SpringBootTest(classes = StartKataApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AccountControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private CustomerRepository customerRepository;

	@Mock
	private AccountService accountService;
	
	@InjectMocks
	private AccountController accountController;
	
	@Mock
	private TransactionService transactionService;
	
	@Captor
	private ArgumentCaptor<Transaction> captor;

	@BeforeEach
	public void init() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(accountController).setControllerAdvice(new CustomGlobalExceptionHandler()).build();
		Customer customer = new Customer("user1");
		customer.setId(1L);
		Account account = new Account(customer);
		account.setAccountId(1L);
		List<Account> list = Stream.of(account).collect(Collectors.toList());
		when(accountService.findAccountById(1L)).thenReturn(Optional.of(account));
		when(accountService.findAccountByCustomer(1L)).thenReturn(list);
		when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
	}

	
	
	@Test
	public void test_deposit_ok() throws UnsupportedEncodingException, Exception {
		double amount = 10.0;
		mockMvc.perform(put("/accounts/deposit/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content("10.0"))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString().equals("10.0");
		
		//assertEquals(amount, accountController.deposit(1L, amount));
		verify(transactionService, times(1)).save(captor.capture());
		Transaction captured = captor.getValue();
	//	assertEquals(1L, captured.getAccount().getAccountId());
		assertEquals(ActionType.DEPOSIT, captured.getActionType());
		assertEquals(amount, captured.getAmount());
	}
	
	@Test
	public void test_deposit_amount_ilegal_KO() throws Exception {
		MvcResult mvcResult = mockMvc.perform(put("/accounts/deposit/1")
					.contentType(MediaType.APPLICATION_JSON)
					.content("-10.0"))
					.andExpect(status().isBadRequest()).andReturn();
		Assertions.assertThat(mvcResult.getResolvedException())
				  .isInstanceOf(IllegalTransException.class);
	}
	
	@Test
	public void test_withdraw_ok() throws UnsupportedEncodingException, Exception {
		Customer customer = new Customer("Tim");
		customer.setId(3L);
		Account account = new Account(customer);
		account.setAccountId(3L);
		double balance = 60;
		account.deposit(balance);
		when(accountService.findAccountById(3L)).thenReturn(Optional.of(account));
		double amount = 20.0;
		mockMvc.perform(put("/accounts/withdraw/3")
				.contentType(MediaType.APPLICATION_JSON)
				.content(" 20.0"))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString().equals("20.0");
		verify(transactionService, times(1)).save(captor.capture());
		Transaction captured = captor.getValue();
		assertEquals(3L, captured.getAccount().getAccountId());
		assertEquals(ActionType.WITHDRAW, captured.getActionType());
		assertEquals(amount, captured.getAmount());
	}
	 
	@Test
	public void test_withdraw_KO_amount_negative() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(put("/accounts/withdraw/1").contentType(MediaType.APPLICATION_JSON).content("-20.0"))
				.andExpect(status().isBadRequest()).andReturn();

		Assertions.assertThat(mvcResult.getResolvedException()).isInstanceOf(IllegalTransException.class)
				.extracting("type").isEqualTo(ErrorAmountType.NEGATIVE_AMOUNT);
	}
   
   @Test
   public void test_withdraw_KO_amount_out_of_range() throws Exception {
	   MvcResult mvcResult = mockMvc
				.perform(put("/accounts/withdraw/1").contentType(MediaType.APPLICATION_JSON).content("20.0"))
				.andExpect(status().isBadRequest()).andReturn();

		Assertions.assertThat(mvcResult.getResolvedException()).isInstanceOf(IllegalTransException.class)
				.extracting("type").isEqualTo(ErrorAmountType.OUT_OF_RANGE_AMOUNT);
    }
   
   @Test
	public void test_get_account_by_id_OK() throws Exception {
		mockMvc.perform(get("/accounts/1"))
				/* .andDo(print()) */
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.accountId", is(1)))
				.andExpect(jsonPath("$.balance", is(0.0)))
				.andExpect(jsonPath("$.customer.id", is(1)))
				.andExpect(jsonPath("$.customer.name", is("user1")));
		verify(accountService, times(1)).findAccountById(1L);
	}
	
	
	@Test
	public void test_get_account_by_Custom_id_OK() throws Exception {
		mockMvc.perform(get("/accounts/customer/1"))
				/* .andDo(print()) */
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].accountId", is(1)))
				.andExpect(jsonPath("$[0].balance", is(0.0)))
				.andExpect(jsonPath("$[0].customer.id", is(1)))
				.andExpect(jsonPath("$[0].customer.name", is("user1")));
		verify(accountService, times(1)).findAccountByCustomer(1L);
		verify(customerRepository, times(1)).findById(1L);
	}

}
