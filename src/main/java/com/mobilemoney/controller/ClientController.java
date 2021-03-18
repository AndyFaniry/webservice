package com.mobilemoney.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobilemoney.model.Client;
import com.mobilemoney.model.ClientRepository;



@RestController
public class ClientController {
	
	@Autowired
	public ClientRepository clientRepository;
	
	@GetMapping("/all")
	public List<Client> getAllClient(){
		return clientRepository.findAll();
	}
	
	@PostMapping(value="/create")
	public String createClient(@RequestBody Client client) {
		Client insertClient = clientRepository.insert(client);
		return "client creer"+insertClient.getNom();
	}
}
