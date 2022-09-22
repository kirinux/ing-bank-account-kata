package fr.ing.interview.bankaccountkata.service.mapper.impl;

import fr.ing.interview.bankaccountkata.model.Client;
import fr.ing.interview.bankaccountkata.model.dto.ClientDTO;
import fr.ing.interview.bankaccountkata.service.mapper.AccountMapper;
import fr.ing.interview.bankaccountkata.service.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Collections;
import java.util.List;

@Service
public class ClientMapperImpl implements ClientMapper {

    private final AccountMapper accountMapper;

    @Autowired
    public ClientMapperImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public ClientDTO toClientDTO(Client client) {
        if (client == null) {
            return null;
        }

        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setId(client.getId());
        clientDTO.setFirstName(client.getFirstName());
        clientDTO.setLastName(client.getLastName());
        clientDTO.setAccounts(accountMapper.toListAccountDTO(client.getAccounts()));
        return clientDTO;
    }

    @Override
    public Client fromClientDTO(ClientDTO clientDTO) {
        if (clientDTO == null) {
            return null;
        }

        Client client = new Client();

        client.setId(clientDTO.getId());
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setAccounts(accountMapper.fromListAccountDTO(clientDTO.getAccounts()));
        return client;
    }

    @Override
    public List<ClientDTO> toListClientDTO(List<Client> clientList) {
        if (clientList == null) {
            return Collections.emptyList();
        }

        List<ClientDTO> clientDTOList = new ArrayList<>();
        for (Client client : clientList) {
            clientDTOList.add(toClientDTO(client));
        }

        return clientDTOList;
    }

    @Override
    public List<Client> fromListClientDTO(List<ClientDTO> clientDTOList) {
        if (clientDTOList == null) {
            return Collections.emptyList();
        }

        List<Client> clientList = new ArrayList<>();
        for (ClientDTO client : clientDTOList) {
            clientList.add(fromClientDTO(client));
        }

        return clientList;
    }
}
