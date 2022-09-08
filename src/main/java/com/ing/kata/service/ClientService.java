package com.ing.kata.service;

import com.ing.kata.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    Client save(Client client);
    List<Client> findAll();
    Optional<Client> findById(String id);
}
