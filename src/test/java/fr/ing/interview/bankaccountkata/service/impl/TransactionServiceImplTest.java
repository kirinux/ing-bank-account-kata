package fr.ing.interview.bankaccountkata.service.impl;

import fr.ing.interview.bankaccountkata.exception.MoneyAmountException;
import fr.ing.interview.bankaccountkata.exception.MoneyDepositNotPossibleException;
import fr.ing.interview.bankaccountkata.exception.MoneyWithdrawalNotPossibleException;
import fr.ing.interview.bankaccountkata.model.dto.AccountDTO;
import fr.ing.interview.bankaccountkata.model.dto.ClientDTO;
import fr.ing.interview.bankaccountkata.model.dto.TransactionDTO;
import fr.ing.interview.bankaccountkata.model.enums.BankEnum;
import fr.ing.interview.bankaccountkata.model.enums.TransactionTypeEnum;
import fr.ing.interview.bankaccountkata.service.AccountService;
import fr.ing.interview.bankaccountkata.service.ClientService;
import fr.ing.interview.bankaccountkata.service.mapper.ClientMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {TransactionServiceImpl.class})
@ExtendWith(SpringExtension.class)
class TransactionServiceImplTest {
    @MockBean
    private AccountService accountService;
    @MockBean
    private ClientMapper clientMapper;
    @MockBean
    private ClientService clientService;
    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

    ClientDTO clientDTO;
    List<ClientDTO> clientDTOList;
    AccountDTO accountDTO;
    List<AccountDTO> accountDTOList;
    TransactionDTO transactionDTO;
    
    @BeforeEach
    public void setUp() {

        transactionDTO = new TransactionDTO(TransactionTypeEnum.DEPOSIT, BigDecimal.valueOf(1), LocalDateTime.now());

        accountDTO = new AccountDTO("AN1", BigDecimal.valueOf(0), BankEnum.SOCIETE_GENERALE, new ArrayList<>());
        accountDTOList = new ArrayList<>();
        accountDTOList.add(accountDTO);

        clientDTO = new ClientDTO("C1", "B.", "Hamed", accountDTOList);
        clientDTOList = new ArrayList<>();
        clientDTOList.add(clientDTO);


    }

    @Test
    void testGetAllClientTransactionsByClientAndByAccountNumber() {
        when(clientMapper.toClientDTO(any())).thenReturn(clientDTO);
        when(accountService.findByAccountNumberFromAccountListThenThrow(any(), any())).thenReturn(accountDTO);
        List<TransactionDTO> transactions = transactionServiceImpl.getAllClientTransactionsByClientAndByAccountNumber("C1", "AN1");
        assertEquals(0, transactions.size());
    }

    @Test
    void testAddTransaction() {
        when(clientMapper.toClientDTO(any())).thenReturn(clientDTO);
        when(accountService.findByAccountNumberFromAccountListThenThrow(any(), any())).thenReturn(accountDTO);
        AccountDTO account = transactionServiceImpl.addTransaction("C1", "AN1", transactionDTO);
        assertEquals(0, BigDecimal.valueOf(1).compareTo(account.getBalance()));
    }

    @Test
    void testTreatTransaction_Throws_MoneyAmountException() {
        transactionDTO.setValue(BigDecimal.valueOf(-2));
        assertThrows(MoneyAmountException.class, () -> transactionServiceImpl.treatTransaction(clientDTO, accountDTO, transactionDTO));
    }

    @Test
    void testTreatTransaction_When_Deposit_Throws_MoneyDepositNotPossibleException() {
        transactionDTO.setValue(BigDecimal.valueOf(0.01));
        assertThrows(MoneyDepositNotPossibleException.class, () -> transactionServiceImpl.treatTransaction(clientDTO, accountDTO, transactionDTO));
    }

    @Test
    void testTreatTransaction_When_Deposit_Ok() {
        transactionDTO.setValue(BigDecimal.valueOf(0.02));
        AccountDTO accountDTOAfterDeposit = transactionServiceImpl.treatTransaction(clientDTO, accountDTO, transactionDTO);
        assertEquals(BigDecimal.valueOf(0.02), accountDTOAfterDeposit.getBalance());
    }

    @Test
    void testTreatTransaction_When_Withdrawal_Throws_MoneyWithdrawalNotPossibleException() {
        accountDTO.setBalance(BigDecimal.valueOf(0.02));
        transactionDTO.setTransactionType(TransactionTypeEnum.WITHDRAWAL);
        assertThrows(MoneyWithdrawalNotPossibleException.class, () -> transactionServiceImpl.treatTransaction(clientDTO, accountDTO, transactionDTO));
    }

    @Test
    void testTreatTransaction_When_Withdrawal_Ok() {
        accountDTO.setBalance(BigDecimal.valueOf(1));
        transactionDTO.setTransactionType(TransactionTypeEnum.WITHDRAWAL);
        AccountDTO accountDTOAfterDeposit = transactionServiceImpl.treatTransaction(clientDTO, accountDTO, transactionDTO);
        assertEquals(0, BigDecimal.valueOf(0).compareTo(accountDTOAfterDeposit.getBalance()));
    }


}

