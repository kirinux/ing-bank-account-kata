package fr.ing.bank.repository;

import fr.ing.bank.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    List<Transaction> findAllByAccountCustomerIdAndAccountId(long customerId, long accountId);
}
