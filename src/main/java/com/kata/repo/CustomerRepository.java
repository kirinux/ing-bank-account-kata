package com.kata.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kata.model.Customer;


public interface CustomerRepository  extends JpaRepository<Customer, Long>{

}
