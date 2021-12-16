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

import fr.ing.interview.bankaccountkata.dto.AccountDto;
import fr.ing.interview.bankaccountkata.service.AccountService;


/**
 * CompteControllerImpl 
 *
 * @author Abir ZEFZEF
 */

@RestController
@RequestMapping("/account")
public class AccountController{

	@Autowired
	private AccountService accountService;
	
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllComptes() {
		
		return ResponseEntity.ok(accountService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getCompteById(@PathVariable Long id) {
		
		return ResponseEntity.ok(accountService.findById(id));
	}

	@PostMapping
	public ResponseEntity<AccountDto> createCompte(@RequestBody AccountDto compteDto) {
		return ResponseEntity.ok(accountService.create(compteDto));
	}

	@PutMapping
	public ResponseEntity<AccountDto> updateCompte(@RequestBody AccountDto compteDto) {
		return ResponseEntity.ok(accountService.update(compteDto));

	}

	@DeleteMapping("/{id}")
	public void deleteCompte(@PathVariable Long id) {
		accountService.delete(id);

	}

	@GetMapping("balance/{id}")
	public ResponseEntity<Double> getAccountBalanceByClientId(@PathVariable Long id) {
		
		return ResponseEntity.ok(accountService.getAccountBalanceByClientId(id));
	}
	
}
