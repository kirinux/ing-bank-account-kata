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

import fr.ing.interview.bankaccountkata.dto.ClientDto;
import fr.ing.interview.bankaccountkata.service.ClientService;


/**
 * ClientControllerImpl 
 *
 * @author Abir ZEFZEF
 */

@RestController
@RequestMapping("/client")
public class ClientController{

	@Autowired
	private ClientService clientService;
	
	@GetMapping
	public ResponseEntity<List<ClientDto>> getAllClients() {
		
		return ResponseEntity.ok(clientService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClientDto> getClientById(@PathVariable Long id) {
		
		return ResponseEntity.ok(clientService.findById(id));
	}

	@PostMapping
	public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto) {
		return ResponseEntity.ok(clientService.create(clientDto));
	}

	@PutMapping
	public ResponseEntity<ClientDto> updateClient(@RequestBody ClientDto clientDto) {
		return ResponseEntity.ok(clientService.update(clientDto));

	}

	@DeleteMapping("/{id}")
	public void deleteClient(@PathVariable Long id) {
		clientService.delete(id);

		
	}

}
