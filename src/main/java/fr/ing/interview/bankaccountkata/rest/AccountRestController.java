package fr.ing.interview.bankaccountkata.rest;


import fr.ing.interview.bankaccountkata.exception.AccountNotFoundException;
import fr.ing.interview.bankaccountkata.exception.OperationNotAllowedException;
import fr.ing.interview.bankaccountkata.model.Account;
import fr.ing.interview.bankaccountkata.model.Customer;
import fr.ing.interview.bankaccountkata.model.Transaction;
import fr.ing.interview.bankaccountkata.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/accounts")
public class AccountRestController {

    @Autowired
    AccountService accountService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> getAccount(@PathVariable Integer id) {
        Optional<Account> account = accountService.getAccount(id);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/deposit")
    public ResponseEntity<Account> deposit(@RequestParam Integer id,
                                                @RequestParam double amount)   {
        try {
        Optional<Account> account = accountService.getAccount(id);
        if (!account.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        if (account.get().getBalance() < 0.01) {
            return ResponseEntity.notFound().build();
        }

         accountService.deposit(amount, account.get());
        Optional<Account> result = accountService.getAccount(id);

        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    } catch (AccountNotFoundException |OperationNotAllowedException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(),e );
     }catch (Exception e) {
        e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(),e );
    }

}

    @PostMapping(value = "/withdraw")
    public ResponseEntity<Account> withdrawMoney(@RequestParam Integer id,
                                                 @RequestParam Double amount) throws Exception {
        try{
        Optional<Account> account = accountService.getAccount(id);
        if (!account.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        if (account.get().getBalance() < 0) {
            return ResponseEntity.badRequest().build();
        }
        Account accountRes = accountService.withdraw(amount, account.get());
        Optional<Account> result = accountService.getAccount(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    } catch (AccountNotFoundException |OperationNotAllowedException e) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(),e );
    }catch (Exception e) {
        e.printStackTrace();
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(),e );
    }
    }

    @GetMapping(value = "/transactions-history")
    public ResponseEntity<List<Transaction>> getTransactionsHistory(@RequestParam Integer idCustomer, @RequestParam Integer idAcount) {
        Customer customer = accountService.getCustomer(idCustomer).get();
        List<Transaction> transactionsHistory = accountService.getTransactions(customer, idAcount);
        return  ResponseEntity.ok(transactionsHistory);
    }



    @GetMapping(value = "/balance/{idAccount}")
    public ResponseEntity<Double> getBalance(@PathVariable Integer idAccount) throws Exception {
        try{
            double res = accountService.getBalance(idAccount);
            return ResponseEntity.ok(res);

        } catch (AccountNotFoundException |OperationNotAllowedException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(),e );
        }catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(),e );
        }
    }


}
