package fr.ing.interview.bankaccountkata.repository;

import fr.ing.interview.bankaccountkata.model.BankAccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<BankAccountModel, Long> {
}
