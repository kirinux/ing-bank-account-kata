package fr.ing.interview.bankaccountkata.dao;

import fr.ing.interview.bankaccountkata.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction,Integer> {
}
