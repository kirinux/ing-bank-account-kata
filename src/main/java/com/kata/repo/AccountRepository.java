package com.kata.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kata.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	@Query("SELECT a FROM Account a JOIN FETCH a.customer c WHERE c.id = :customerId")
	List<Account> findAccountsByCustomer(@Param("customerId")long customerId);
	
}
