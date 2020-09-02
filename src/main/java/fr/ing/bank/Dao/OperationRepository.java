package fr.ing.bank.Dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.ing.bank.Entities.OperationEntity;

public interface OperationRepository extends JpaRepository<OperationEntity, Long>{
    
     
    @Query("select o from operation o where o.account.numAccount=:x")
    public Page<OperationEntity> getOperations( @Param("x") long code, Pageable pageable);
    
    
}