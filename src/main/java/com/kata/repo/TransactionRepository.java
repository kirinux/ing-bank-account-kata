package com.kata.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kata.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	@Query("SELECT t FROM Transaction t JOIN FETCH t.account a WHERE a.accountId = :id")
	List<Transaction> findTransByAccount(@Param("id")long id);
}
