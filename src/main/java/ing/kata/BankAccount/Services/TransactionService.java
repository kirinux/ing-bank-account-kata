package ing.kata.BankAccount.Services;

import ing.kata.BankAccount.Dtos.TransactionDto;
import ing.kata.BankAccount.Entities.Transaction;
import ing.kata.BankAccount.Enums.TransactionStatus;
import ing.kata.BankAccount.Exceptions.NotValidTransactionException;
import ing.kata.BankAccount.Repositories.TransactionRepository;
import ing.kata.BankAccount.mappers.BankAccountMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {


    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Transactional
    public TransactionDto createTransaction(TransactionDto transactionDto, long accountId) {

        Transaction transaction = BankAccountMapper.INSTANCE.toTransaction(transactionDto);

        try {
            transaction = accountService.processTransaction(transaction, accountId);
            transaction.setStatus(TransactionStatus.ACCEPTED);
        } catch (NotValidTransactionException exception) {
            transaction.setStatus(TransactionStatus.REFUSED);
        }

        return BankAccountMapper.INSTANCE.toTransactionDto(transactionRepository.save(transaction));
    }

    public List<TransactionDto> getTransactionsByAccount(long accountId) {
        return transactionRepository.findByAccountId(accountId).stream().map(BankAccountMapper.INSTANCE::toTransactionDto).collect(Collectors.toList());

    }

    public List<TransactionDto> getTransactionsByCustomer(long customerId) {

        return transactionRepository.findByAccountCustomerId(customerId).stream().map(BankAccountMapper.INSTANCE::toTransactionDto).collect(Collectors.toList());

    }
}
