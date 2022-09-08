package com.ing.kata.controller;

import com.ing.kata.model.Client;
import com.ing.kata.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public List<Client> getAll() {
        return this.clientService.findAll();
    }

    @PostMapping
    public Client save(@RequestBody Client client) {
        return this.clientService.save(client);
    }

    @GetMapping("/{id}")
    public Optional<Client> getById(@PathVariable String id) {
        return this.clientService.findById(id);
    }
}
