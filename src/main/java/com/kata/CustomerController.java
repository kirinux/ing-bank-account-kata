package com.kata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kata.error.ObjectNotFoundException;
import com.kata.error.ObjectNotFoundException.ObjectType;
import com.kata.model.Customer;
import com.kata.repo.CustomerRepository;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	 // Find
    @GetMapping
    List<Customer> findAll() {
        return customerRepository.findAll();
    }
    
    @GetMapping("/{id}")
    Customer findCustomer(@PathVariable Long id) {
        return customerRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException(id, ObjectType.CUSTOMER));
               // .orElseThrow(() -> new BookNotFoundException(id));
    }


}
