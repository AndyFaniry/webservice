package com.mobilemoney.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobilemoney.model.Client;
import com.mobilemoney.model.ClientRepository;
import com.mobilemoney.model.Response;


@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
public class ClientController {
	
	@Autowired
	public ClientRepository clientRepository;
	
	@GetMapping("/client/findall")
	public Response getAllClient(){
		Response reponse= new Response();
		reponse.data= clientRepository.findAll();
		reponse.message= null;
		reponse.code= "200" ;
		return reponse;
	}
	
	@PostMapping(value="/client/create")
	public Response createClient(@RequestBody Client client) {
		Response reponse= new Response();
		Client insertClient = clientRepository.insert(client);
		reponse.data= insertClient;
		reponse.message= null;
		reponse.code= "200" ;
		return reponse;
	}
}
