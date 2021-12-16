package fr.ing.interview.bankaccountkata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.ing.interview.bankaccountkata.entity.Transaction;

/**
 * Repository of {@link Transaction}
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	@Query("select transaction from Transaction transaction where transaction.client.id = :clientId and transaction.account.id = :accountId")
	List<Transaction> findAllByAccountAndClient(@Param("accountId") Long accountId, @Param("clientId") Long clientId);
}
