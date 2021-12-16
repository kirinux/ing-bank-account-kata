package fr.ing.interview.bankaccountkata.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.ing.interview.bankaccountkata.entity.Account;

/**
 * Repository of {@link Account}
 */
public interface AccountRepository extends JpaRepository<Account, Long>{
	
	@Query("select account from Account account where account.client.id = :clientId")
	Optional<Account> findByClientId(@Param("clientId") Long clientId);
	
	

}
