package fr.ing.interview.bankaccountkata.service;

import fr.ing.interview.bankaccountkata.dao.AccountRepository;
import fr.ing.interview.bankaccountkata.dao.CustomerRepository;
import fr.ing.interview.bankaccountkata.dao.TransactionRepository;
import fr.ing.interview.bankaccountkata.exception.AccountNotFoundException;
import fr.ing.interview.bankaccountkata.exception.OperationNotAllowedException;
import fr.ing.interview.bankaccountkata.model.Account;
import fr.ing.interview.bankaccountkata.model.Customer;
import fr.ing.interview.bankaccountkata.model.EnumTransactionType;
import fr.ing.interview.bankaccountkata.model.Transaction;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    public static final double AMOUNT_MIN = 0.01;
    public static final String MINIMUM_AMOUNT_DEPOSIT_ERROR_MSG = "Operation faild : the minimum amount allowed is : "+AMOUNT_MIN;
    public static final String INSUFFICIENT_SOLD_ERROR_MSG = "Operation faild : insufficient sold " ;
    public static final String ACCOUNT_NOT_FOUND_ERROR_MSG = "Account not found !" ;

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Account deposit(double amount, Account account) throws   AccountNotFoundException,OperationNotAllowedException {
        if (account == null) {
            throw new AccountNotFoundException(ACCOUNT_NOT_FOUND_ERROR_MSG);
        }
        if (amount < AMOUNT_MIN) {
            throw new OperationNotAllowedException(MINIMUM_AMOUNT_DEPOSIT_ERROR_MSG);
        }
        account.setBalance((account.getBalance() + amount));
        Transaction t = new Transaction(EnumTransactionType.DEPOSIT.getValue(), amount, account,LocalDateTime.now());
        account.getTransactions().add(t);
        accountRepository.save(account);
        transactionRepository.save(new Transaction(EnumTransactionType.DEPOSIT.getValue(), amount, account,LocalDateTime.now()));
        return account;
    }

    @Override
    public Account withdraw(double amount, Account account) throws AccountNotFoundException,OperationNotAllowedException {
        if (account == null) {
            throw new AccountNotFoundException(ACCOUNT_NOT_FOUND_ERROR_MSG);
        }
        if ((account.getBalance() - amount) < 0) {
            throw new OperationNotAllowedException(INSUFFICIENT_SOLD_ERROR_MSG);
        }
        account.setBalance((account.getBalance() - amount));
        Transaction t = new Transaction(EnumTransactionType.WITH_DRAW.getValue(),   amount, account,LocalDateTime.now());
        account.getTransactions().add(t);
        accountRepository.save(account);
        transactionRepository.save(t);
        return account;
    }

    /***
     *
     * @param idAccount
     * @return
     * @throws Exception
     */
    @Override
    public double getBalance( int idAccount) throws Exception {
        Optional<Account> account = accountRepository.findById(idAccount);
        if(account.isPresent()) return account.get().getBalance();
        else {             throw new AccountNotFoundException(ACCOUNT_NOT_FOUND_ERROR_MSG);
        }
    }

    /***
     *
     * @param customer
     * @param idAccount
     * @return
     */
    public List<Transaction> getTransactions(Customer customer, long idAccount) {
        return customer.getAccounts().stream().filter(a -> a.getIdAccount() == idAccount).findFirst().get().getTransactions();
    }

    /***
     *
     * @param idAccount
     * @return
     */
    @Override
    public Optional<Account> getAccount(int idAccount) {
        return accountRepository.findById(idAccount);
    }

    /***
     *
     * @param idCustomer
     * @return
     */
    @Override
    public Optional<Customer> getCustomer(int idCustomer) {
        return customerRepository.findById(idCustomer) ;
    }



}
