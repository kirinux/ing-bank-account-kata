package fr.ing.interview.bankaccountkata.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import fr.ing.interview.bankaccountkata.controller.ClientController;
import fr.ing.interview.bankaccountkata.dto.ClientDto;
import fr.ing.interview.bankaccountkata.entity.Client;
import fr.ing.interview.bankaccountkata.service.ClientService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientControllerTest {

	@Mock
	private ClientService clientService;
	
	@InjectMocks
	private ClientController clientControllerImpl;
	
	ClientDto clientDto;
	Client client;
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
	mockMvc = MockMvcBuilders.standaloneSetup(clientControllerImpl).build();

	clientDto = new ClientDto(1L, "nom", "prenom", "rib", LocalDate.now());
	client= new Client(1L, "nom", "prenom", "rib", LocalDate.now());
	}
	
	
	@Test
	public final void test_Should_Return_200_OK_getAll() throws Exception {

		when(clientService.getAll()).thenReturn(Arrays.asList(clientDto));	
		mockMvc.perform(get("/client")
				)
				.andExpect(status().isOk());

	}
	
	
	@Test
	public final void test_Should_Return_200_OK_getClientById() throws Exception {

		when(clientService.findById(Mockito.anyLong())).thenReturn(clientDto);	
		mockMvc.perform(get("/client/{id}",1L)
				)
				.andExpect(status().isOk());

	}
	
	
	@Test
	public final void test_Should_Return_200_OK_createClient() throws Exception {

		when(clientService.create(Mockito.any(ClientDto.class))).thenReturn(clientDto);	
		mockMvc.perform(post("/client")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(asJsonString(clientDto)))
				.andExpect(status().isOk());

	}
	
	@Test
	public final void test_Should_Return_200_OK_updateClient() throws Exception {

		when(clientService.create(Mockito.any(ClientDto.class))).thenReturn(clientDto);	
		mockMvc.perform(put("/client")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(asJsonString(clientDto)))
				.andExpect(status().isOk());

	}
	
	@Test
	public final void test_Should_Return_200_OK_deleteClient() throws Exception {

		when(clientService.create(Mockito.any(ClientDto.class))).thenReturn(clientDto);	
		mockMvc.perform(delete("/client/{id}",1L)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(asJsonString(clientDto)))
				.andExpect(status().isOk());

	}
	
	// Méthode pour transformer un objet en json
	public static String asJsonString(final Object obj) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			// Configuration qui permet de garder les dates localdate en format json
			// "yyyy-MM-ddT00:00:00:000"
			mapper.registerModule(new JavaTimeModule());
			mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
