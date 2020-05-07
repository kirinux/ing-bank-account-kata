package fr.ing.interview.bankaccountkata.repository;

import fr.ing.interview.bankaccountkata.entities.Bank;
import org.springframework.data.repository.CrudRepository;

/**
 * @author kso
 */
public interface BankRepository extends CrudRepository<Bank, Integer> {
}
