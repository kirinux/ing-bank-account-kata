package ing.kata.BankAccount.Services;

import ing.kata.BankAccount.Dtos.CustomerDto;
import ing.kata.BankAccount.Repositories.CustomerRepository;
import ing.kata.BankAccount.mappers.BankAccountMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public CustomerDto createCustomer(CustomerDto customerDto) {
        return BankAccountMapper.INSTANCE.toCustomerDto(customerRepository.save(BankAccountMapper.INSTANCE.toCustomer(customerDto)));
    }
}
