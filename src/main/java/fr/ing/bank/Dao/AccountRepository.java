package fr.ing.bank.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.ing.bank.Entities.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {

	
	public AccountEntity findById(long codeAccount);
	
}
