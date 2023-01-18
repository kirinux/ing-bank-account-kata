package ing.kata.BankAccount.Controllers;

import ing.kata.BankAccount.Dtos.CustomerDto;
import ing.kata.BankAccount.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        return ResponseEntity
                .ok()
                .body(customerService.createCustomer(customerDto));
    }


}
