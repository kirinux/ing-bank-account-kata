package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.*;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.ClientService;

@SpringBootTest
class IngbankaccountApplicationTests {
	@Autowired
	private ClientService clientService;
	@Autowired
	private ClientRepository clientrepo;
	@Autowired
	private AccountRepository accountrepo;
	@Test
	void testAddClient() {
	 	Client client=new Client();
	 	client.setFirstName("Ayoub");
	 	assertNotNull(clientService.addClient(client).getId());
	 }
	
	@Test
	public void  testUpdateClient() {
		
		Client client=clientrepo.findById((long)5).get();
		client.setLastName("test");
		assertEquals(clientService.addClient(client).getLastName(), "test");
		
	}
	
	@Test
	public void testDeleteClient() {
		Client client=clientrepo.findById((long)3).get();
		clientService.deleteClient(client);
		assertFalse(clientrepo.findById((long)3).isPresent());
		
	}
	
	@Test
	public void testdepositAmount() {
		Client client=new Client();
		client.setFirstName("Test");
		Client c=clientService.addClient(client);
		clientService.addAccount(c.getId());
		
	    clientService.deposit(c.getAccountList().get(0).getUid(), 100);
	    assertEquals(100, accountrepo.findByUid(c.getAccountList().get(0).getUid()).get().getBalance());
	}
	@Test
	public void testwithAmount() {
		Client client=new Client();
		client.setFirstName("Test");
		Client c=clientService.addClient(client);
		clientService.addAccount(c.getId());
		
	    clientService.deposit(c.getAccountList().get(0).getUid(), 1000);
	    clientService.withdraw(c.getAccountList().get(0).getUid(), 500);
	    
	    assertEquals(500, accountrepo.findByUid(c.getAccountList().get(0).getUid()).get().getBalance());
	}

}
