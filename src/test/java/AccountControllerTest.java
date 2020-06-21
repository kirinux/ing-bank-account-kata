import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.kata.AccountController;
import com.kata.StartKataApplication;
import com.kata.model.Account;
import com.kata.model.Customer;
import com.kata.repo.CustomerRepository;
import com.kata.service.AccountService;

@SpringBootTest(classes = StartKataApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AccountControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private CustomerRepository mockRepository;

	@Mock
	private AccountService accountService;
	
	@InjectMocks
	private AccountController accountController;

	@BeforeEach
	public void init() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
		Customer customer = new Customer("user1");
		customer.setId(1L);
		Account account = new Account(customer);
		account.setAccountId(1L);
		List<Account> list = Stream.of(account).collect(Collectors.toList());
		when(accountService.findAccountById(1L)).thenReturn(Optional.of(account));
	}

	@Test
	public void test_get_accout_by_id_OK() throws Exception {
		mockMvc.perform(get("/accounts/1"))
				/* .andDo(print()) */
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.accountId", is(1)))
				.andExpect(jsonPath("$.balance", is(0.0)))
				.andExpect(jsonPath("$.customer.id", is(1)))
				.andExpect(jsonPath("$.customer.name", is("user1")));
		verify(accountService, times(1)).findAccountById(1L);
	}

}
