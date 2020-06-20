package fr.ing.interview.bankaccountkata.dao;


import fr.ing.interview.bankaccountkata.model.Customer;
import org.springframework.data.repository.CrudRepository;


public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
