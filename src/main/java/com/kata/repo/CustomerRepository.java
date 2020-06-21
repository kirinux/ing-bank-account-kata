package com.kata.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kata.model.Customer;


public interface CustomerRepository  extends JpaRepository<Customer, Long>{

}
