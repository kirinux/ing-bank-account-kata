package fr.ing.bank.repository;

import fr.ing.bank.model.Account;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    List<Account> findAllByCustomerId(long customerId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Account findByCustomerIdAndId(long customerId, long id);
}
