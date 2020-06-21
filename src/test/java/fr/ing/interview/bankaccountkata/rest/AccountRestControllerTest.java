package fr.ing.interview.bankaccountkata.rest;

import fr.ing.interview.bankaccountkata.dao.AccountRepository;
import fr.ing.interview.bankaccountkata.model.Account;
import fr.ing.interview.bankaccountkata.model.Customer;
import fr.ing.interview.bankaccountkata.model.EnumTransactionType;
import fr.ing.interview.bankaccountkata.model.Transaction;
import fr.ing.interview.bankaccountkata.service.AccountService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ActiveProfiles("test")
@SpringBootTest
class AccountRestControllerTest {


    private MockMvc mvc;
    @InjectMocks
    private AccountRestController accountRestController;
    @Mock
    private AccountService accountService;
    @Mock
    private AccountRepository accountRepository;


    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(accountRestController).build();
    }

    @Test
    void shouldeGetBalanceBalance() throws Exception {
        Integer id = 1;
        when( accountService.getBalance(id)).thenReturn(Double.valueOf(50));
        mvc.perform(
                MockMvcRequestBuilders.get("/api/accounts/balance/1")
        ).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("50.0") );
    }

    @Test
    void shouldDepositMoneyAndReturnOkStatus() throws Exception{
        Integer id = 1;
        Account account = new Account(1,100,null );
        Account accountRes = new Account(1,150,null );
        when(accountService.deposit(50,account)).thenReturn(accountRes);
        when(accountService.getAccount(id)).thenReturn(java.util.Optional.ofNullable(account),java.util.Optional.ofNullable(accountRes));
        mvc.perform(
                MockMvcRequestBuilders.post("/api/accounts/deposit?amount=100&id=1")
        ).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.idAccount", Matchers.is(1)))
                .andExpect(jsonPath("$.balance",  Matchers.is(150.0)));
    }

    @Test
    void shouldWithdrawMoneyAndReturnOkStatus() throws Exception {
        Integer id = 1;
        Account account = new Account(1,150,null );
        Account accountRes = new Account(1,100,null );
        when(accountService.withdraw(50,account)).thenReturn(accountRes);
        when(accountService.getAccount(id)).thenReturn(java.util.Optional.ofNullable(account),java.util.Optional.ofNullable(accountRes));
        mvc.perform(
                MockMvcRequestBuilders.post("/api/accounts/withdraw?amount=100&id=1")
        ).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.idAccount", Matchers.is(1)))
                .andExpect(jsonPath("$.balance",  Matchers.is(100.0)));
    }

    @Test
    void shouldGetTransactionsHistory() throws Exception {
        int id=1;
        Account account = new Account(1,100,null );
        Transaction t1 = new Transaction(EnumTransactionType.WITH_DRAW.getValue(),   80, account, LocalDateTime.now());
        Transaction t2 = new Transaction(EnumTransactionType.DEPOSIT.getValue(),   100, account, LocalDateTime.now());
        Transaction t3 = new Transaction(EnumTransactionType.WITH_DRAW.getValue(),   40, account, LocalDateTime.now());
        List<Transaction> transactionsHistory = Arrays.asList(t1,t2,t3);
        Customer c = new Customer();
        c.setIdCustomer(1);
        account.setTransactions(transactionsHistory);
        c.setAccounts(Arrays.asList(account));
        when(accountService.getTransactions(c, id)).thenReturn(transactionsHistory);
        when(accountService.getCustomer(id)).thenReturn(java.util.Optional.ofNullable(c));

        mvc.perform(
                MockMvcRequestBuilders.get("/api/accounts/transactions-history?idCustomer=1&idAcount=1")
        ).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Matchers.is(3)))
                .andExpect(jsonPath("$.[0].amount", Matchers.is(80.0)));
    }

    @Test
    void shouldAindAccountAndOkStatus() throws Exception{
        Integer id = 1;
        Account account = new Account(1,100,null );
        when(accountService.getAccount(id)).thenReturn(java.util.Optional.ofNullable(account));
        mvc.perform(
                MockMvcRequestBuilders.get("/api/accounts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idAccount", Matchers.is(1)))
                .andExpect(jsonPath("$.balance",  Matchers.is(100.0)));
    }
}
