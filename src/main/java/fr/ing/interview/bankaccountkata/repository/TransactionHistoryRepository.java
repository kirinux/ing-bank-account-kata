package fr.ing.interview.bankaccountkata.repository;

import fr.ing.interview.bankaccountkata.entities.TransactionHistory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author kso
 */
public interface TransactionHistoryRepository extends CrudRepository<TransactionHistory, Integer> {

    List<TransactionHistory> findByAccountId(Integer id);
}
