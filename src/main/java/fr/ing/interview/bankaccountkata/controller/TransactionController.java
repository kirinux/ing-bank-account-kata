package fr.ing.interview.bankaccountkata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.ing.interview.bankaccountkata.dto.TransactionDto;
import fr.ing.interview.bankaccountkata.service.TransactionService;


/**
 * TransactionControllerImpl 
 *
 * @author Abir ZEFZEF
 */

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	private TransactionService clientService;
	
	@GetMapping
	public ResponseEntity<List<TransactionDto>> getAllTransactions() {
		
		return ResponseEntity.ok(clientService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<TransactionDto> getTransactionById(@PathVariable Long id) {
		
		return ResponseEntity.ok(clientService.findById(id));
	}

	@PostMapping
	public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto) {
		return ResponseEntity.ok(clientService.create(transactionDto));
	}

	@PutMapping
	public ResponseEntity<TransactionDto> updateTransaction(@RequestBody TransactionDto transactionDto) {
		return ResponseEntity.ok(clientService.update(transactionDto));

	}

	@DeleteMapping("/{id}")
	public void deleteTransaction(@PathVariable Long id) {
		clientService.delete(id);

		
	}

	@GetMapping("historique/{accountId}/{clientId}")
	public ResponseEntity<List<TransactionDto>> getAllTransactionsByClientAndCompte(@PathVariable Long accountId, @PathVariable  Long clientId) {
		
		return ResponseEntity.ok(clientService.getAllByAccountAndClient(accountId, clientId));
	}
	
	
}
