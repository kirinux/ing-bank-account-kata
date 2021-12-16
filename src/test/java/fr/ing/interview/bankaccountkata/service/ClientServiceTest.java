package fr.ing.interview.bankaccountkata.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import fr.ing.interview.bankaccountkata.dto.ClientDto;
import fr.ing.interview.bankaccountkata.entity.Client;
import fr.ing.interview.bankaccountkata.mapper.ClientMapper;
import fr.ing.interview.bankaccountkata.repository.ClientRepository;
import fr.ing.interview.bankaccountkata.service.ClientService;

@RunWith(SpringRunner.class)
public class ClientServiceTest {

	@Mock
	private ClientRepository clientRepository;
	
	@Mock
	private ClientMapper clientMapper;
		
	@InjectMocks
	private ClientService clientService;
	
	ClientDto clientDto;
	Client client;
	List<Client> clients;
	List<ClientDto> clientsDto;

	@Before
	public void setUp() throws Exception {
	clientDto = new ClientDto(1L, "nom", "prenom", "rib", LocalDate.now());
	client= new Client(1L, "nom", "prenom", "rib", LocalDate.now());
	
	clients = new ArrayList<Client>();
	clients.add(client);
	
	
	clientsDto = new ArrayList<ClientDto>();
	clientsDto.add(clientDto);
	}
	
	@Test
	public final void test_getAll() {

		
		when(clientRepository.findAll()).thenReturn(clients);
		when(clientMapper.clientEntityToClientDto(Mockito.any(Client.class))).thenReturn(clientDto);
		when(clientMapper.toListDto(clients)).thenReturn(clientsDto);
		List<ClientDto> clientsDtoResult = clientService.getAll();
		assertEquals(clientsDtoResult, clientsDto);
	}
	
	@Test
	public final void test_findById() {
		
		when(clientRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(client));

		when(clientMapper.clientEntityToClientDto(Mockito.any(Client.class))).thenReturn(clientDto);
		
		ClientDto clientResult = clientService.findById(clientDto.getId());
		
		assertEquals(clientResult, clientDto);

	}
	
	
	@Test
	public final void test_create() {

		when(clientMapper.clientDtoToClientEntity(Mockito.any(ClientDto.class))).thenReturn(client);

		when(clientRepository.save(Mockito.any(Client.class))).thenReturn(client);
		when(clientMapper.clientEntityToClientDto(Mockito.any(Client.class))).thenReturn(clientDto);

		ClientDto client = clientService.create(clientDto);
		
		assertNotNull(client);
	}
	
	@Test
	public final void test_update() {		
		when(clientRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(client));
		when(clientMapper.clientDtoToClientEntity(Mockito.any(ClientDto.class))).thenReturn(client);
		when(clientRepository.save(Mockito.any(Client.class))).thenReturn(client);
		when(clientMapper.clientEntityToClientDto(Mockito.any(Client.class))).thenReturn(clientDto);

		ClientDto client = clientService.update(clientDto);
		
		assertNotNull(client);
	}

	@Test
	public final void test_delete() {
		when(clientRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(client));

		clientService.delete(clientDto.getId());
		
		verify(clientRepository).delete(client);
		
	}
}
