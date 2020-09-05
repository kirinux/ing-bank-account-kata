package com.example.demo.api;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Account;
import com.example.demo.model.Client;
import com.example.demo.model.Transaction;
import com.example.demo.service.ClientService;

@RestController
public class Controller {
   @Autowired
   private ClientService clientService;
   @PutMapping("addClient")
   public Client addClient(@RequestBody Client c) {
	  
	    return clientService.addClient(c);
   }
   @PutMapping("updateClient")
   public Client updateClient(@RequestBody Client c) {
	   if(clientService.checkClient(c.getId())==null)throw new NotFoundException();
	    return clientService.updateClient(c);
   }
   @GetMapping("getAllClient")
   public List<Client> getAllClient(){
	   return clientService.getAllClient();
   }
   @DeleteMapping("delClient")
   public void deleteClient(@RequestBody Client c) {
	   clientService.deleteClient(c);
   }
   @GetMapping("addAccount")
   public List<Account> addAccount(@Param("id") long id){
	   if(clientService.checkClient(id)==null)throw new NotFoundException();
	   return clientService.addAccount(id);
   }
   @GetMapping("deleAccount")
   public List<Account> deleAccount(@Param("uuid") String uuid,@Param("id") long id){
	   if(clientService.checkAccount(uuid)==null)throw new NotFoundException();
	   return clientService.deleteAccount(uuid, id);
   }
   @PostMapping("deposit")
   public void deposit(@Param("uuid") String uid,@Param("amount") double amount) {
		if(amount<0.01 || clientService.checkAccount(uid)==null) throw new NotFoundException();
		
		clientService.deposit(uid, amount);
		
	} 
   @PostMapping("withdraw")
   public void withdraw(String uid,double amount) {
	   if(clientService.checkAccount(uid)==null) throw new NotFoundException();
		
	   clientService.withdraw(uid, amount);
   }
   @GetMapping("getAllTransaction")
   public List<Transaction> getAllTransaction(@Param("id") long id,@Param("uid") String uid){
	   if(clientService.checkClient(id)==null) throw new NotFoundException();
	   return clientService.getAllTransaction(id, uid);
		
   }
   @GetMapping("getBalance")
   public double getBalance(@Param("uid") String uid) {
	   if(clientService.checkAccount(uid)==null) throw new NotFoundException();
		return clientService.getBalanceAccount(uid);
   }
}
