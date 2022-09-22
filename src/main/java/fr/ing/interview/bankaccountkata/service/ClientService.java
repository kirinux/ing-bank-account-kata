package fr.ing.interview.bankaccountkata.service;

import fr.ing.interview.bankaccountkata.model.Client;
import fr.ing.interview.bankaccountkata.model.dto.ClientDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getAll();
    ClientDTO findById(String id);
    ClientDTO create(ClientDTO clientDTO);
    ClientDTO update(ClientDTO clientDTO);
    void delete(String id);
    Client getClientIfExistThenThrow(String id);
}
