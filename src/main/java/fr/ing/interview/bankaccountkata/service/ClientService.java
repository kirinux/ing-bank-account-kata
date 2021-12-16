package fr.ing.interview.bankaccountkata.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ing.interview.bankaccountkata.dto.ClientDto;
import fr.ing.interview.bankaccountkata.entity.Client;
import fr.ing.interview.bankaccountkata.exception.ClientNotFoundException;
import fr.ing.interview.bankaccountkata.mapper.ClientMapper;
import fr.ing.interview.bankaccountkata.repository.ClientRepository;

/**
 * ClientServiceImpl
 *
 * @author Abir ZEFZEF
 */
@Service
@Transactional
public class ClientService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientMapper clientMapper;

	
	public List<ClientDto> getAll() {

		LOGGER.info("recuperer tous les clients");
		return clientMapper.toListDto(clientRepository.findAll());
		
	}

	
	public ClientDto findById(Long id) {
		LOGGER.info("recuperer le client : {}", id);
		return clientMapper.clientEntityToClientDto(clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id)));
	}

	
	public ClientDto create(ClientDto clientDto) {
		LOGGER.info("creer le client : {}", clientDto.getFirstName());

		return clientMapper.clientEntityToClientDto(
				clientRepository.save(clientMapper.clientDtoToClientEntity(clientDto)));
	}

	
	public ClientDto update(ClientDto clientDto) {
		LOGGER.info("mise a jour le client : {}", clientDto.getFirstName());

		clientRepository.findById(clientDto.getId()).orElseThrow(() -> new ClientNotFoundException(clientDto.getId()));
		Client client = clientMapper.clientDtoToClientEntity(clientDto);
		return clientMapper.clientEntityToClientDto(clientRepository.save(client));
	}

	
	public void delete(Long id) {
		LOGGER.info("suppression le client : {}", id);

		Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
		clientRepository.delete(client);

	}



}
