package fr.ing.interview.bankaccountkata.dao;

import fr.ing.interview.bankaccountkata.model.Account;
import fr.ing.interview.bankaccountkata.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction,Integer> {



    List<Transaction> findByAccount(Account idAccount);
}
