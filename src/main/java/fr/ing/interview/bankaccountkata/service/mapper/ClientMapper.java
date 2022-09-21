package fr.ing.interview.bankaccountkata.service.mapper;

import fr.ing.interview.bankaccountkata.model.Client;
import fr.ing.interview.bankaccountkata.model.dto.ClientDTO;

import java.util.List;

public interface ClientMapper {
    ClientDTO toClientDTO(Client client);
    Client fromClientDTO(ClientDTO clientDTO);
    List<ClientDTO> toListClientDTO(List<Client> clientList);
    List<Client> fromListClientDTO(List<ClientDTO> clientDTOList);
}
