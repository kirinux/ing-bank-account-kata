package fr.ing.interview.bankaccountkata.repository;

import fr.ing.interview.bankaccountkata.model.OperationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<OperationModel, Long> {
    List<OperationModel> findAllByAccountId(long accountId);
}
