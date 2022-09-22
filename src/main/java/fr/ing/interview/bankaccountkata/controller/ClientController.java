package fr.ing.interview.bankaccountkata.controller;

import fr.ing.interview.bankaccountkata.model.dto.ClientDTO;
import fr.ing.interview.bankaccountkata.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientDTO> getAllClients() {
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable String id) {
        return clientService.findById(id);
    }

    @PostMapping
    public ClientDTO createClient(@RequestBody ClientDTO clientDTO) {
        return clientService.create(clientDTO);
    }

    @PutMapping
    public ClientDTO updateClient(@RequestBody ClientDTO clientDTO) {
        return clientService.update(clientDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable String id) {
        clientService.delete(id);
    }
}
