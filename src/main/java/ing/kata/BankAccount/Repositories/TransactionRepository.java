package ing.kata.BankAccount.Repositories;

import ing.kata.BankAccount.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountId(long accountId);

    List<Transaction> findByAccountCustomerId(long accountId);
}
