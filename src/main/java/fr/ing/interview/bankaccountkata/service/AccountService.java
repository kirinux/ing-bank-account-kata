package fr.ing.interview.bankaccountkata.service;

import fr.ing.interview.bankaccountkata.exception.AccountNotFoundException;
import fr.ing.interview.bankaccountkata.exception.OperationNotAllowedException;
import fr.ing.interview.bankaccountkata.model.Account;
import fr.ing.interview.bankaccountkata.model.Customer;
import fr.ing.interview.bankaccountkata.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface AccountService {

    Account deposit(double amount,Account account) throws AccountNotFoundException,OperationNotAllowedException;
    Account withdraw(double amount, Account account) throws AccountNotFoundException,OperationNotAllowedException;
    double getBalance(int idAccount) throws Exception;
    List<Transaction> getTransactions(Customer customer,long idAccount);
    Optional<Account> getAccount(int idAccount);
    Optional<Customer> getCustomer(int idCustomer);

}
