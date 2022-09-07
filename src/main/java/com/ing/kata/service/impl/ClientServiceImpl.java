package com.ing.kata.service.impl;

import com.ing.kata.model.Client;
import com.ing.kata.repository.ClientRepository;
import com.ing.kata.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client save(Client client) {
        return this.clientRepository.save(client);
    }

    @Override
    public List<Client> findAll() {
        return this.clientRepository.findAll();
    }

    @Override
    public Optional<Client> findById(String id) {
        return this.clientRepository.findById(id);
    }
}
