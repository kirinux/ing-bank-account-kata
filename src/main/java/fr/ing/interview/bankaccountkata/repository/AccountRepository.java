package fr.ing.interview.bankaccountkata.repository;

import fr.ing.interview.bankaccountkata.entities.Account;
import org.springframework.data.repository.CrudRepository;

/**
 * @author kso
 */
public interface AccountRepository extends CrudRepository<Account, Integer> {
}
