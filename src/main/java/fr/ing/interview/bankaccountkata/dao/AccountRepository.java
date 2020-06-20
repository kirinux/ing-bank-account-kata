package fr.ing.interview.bankaccountkata.dao;

import fr.ing.interview.bankaccountkata.model.Account;
import org.springframework.data.repository.CrudRepository;



public interface AccountRepository extends CrudRepository<Account, Integer> {
}
