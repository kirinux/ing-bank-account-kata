package fr.ing.interview.bankaccountkata.service.impl;

import fr.ing.interview.bankaccountkata.exception.ClientCreationNotPossibleException;
import fr.ing.interview.bankaccountkata.exception.ClientNotFoundException;
import fr.ing.interview.bankaccountkata.model.Client;
import fr.ing.interview.bankaccountkata.model.dto.ClientDTO;
import fr.ing.interview.bankaccountkata.repository.ClientRepository;
import fr.ing.interview.bankaccountkata.service.ClientService;
import fr.ing.interview.bankaccountkata.service.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public List<ClientDTO> getAll() {
        return clientMapper.toListClientDTO(clientRepository.findAll());

    }

    @Override
    public ClientDTO findById(String id) {
        return clientMapper.toClientDTO(getClientIfExistThenThrow(id));
    }

    @Override
    public ClientDTO create(ClientDTO clientDTO) {
        Optional<Client> client = Optional.empty();
        if (clientDTO.getId() != null) {
            client = clientRepository.findById(clientDTO.getId());
        }
        if (client.isPresent()) {
            throw new ClientCreationNotPossibleException(clientDTO.getId());
        }
        return clientMapper.toClientDTO(clientRepository.save(clientMapper.fromClientDTO(clientDTO)));
    }

    @Override
    public ClientDTO update(ClientDTO clientDTO) {
        if (getClientIfExist(clientDTO.getId()) == null) {
            throw new ClientNotFoundException(clientDTO.getId());
        }
        Client client = clientMapper.fromClientDTO(clientDTO);
        return clientMapper.toClientDTO(clientRepository.save(client));
    }

    @Override
    public void delete(String id) {
        clientRepository.delete(getClientIfExistThenThrow(id));
    }

    @Override
    public Client getClientIfExistThenThrow(String id) {
        Client client = getClientIfExist(id);
        if (client != null) {
            return client;
        }
        throw new ClientNotFoundException(id);
    }

    public Client getClientIfExist(String id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.orElse(null);
    }

}
