package com.mobilemoney.controller;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobilemoney.bdb.ConnectionPstg;
import com.mobilemoney.model.Compte;
import com.mobilemoney.model.Response;
//import com.mobilemoney.bdb.ConnectionPstg;

@RestController
public class CompteController {
	@PostMapping(value="/login")
	public Response createClient(@RequestBody Compte compte) throws Exception {
		Connection co= new ConnectionPstg().getConnection();
		Response reponse= new Response();
		try {
			Compte compteValide= Compte.valideLogin(compte.getNum(),compte.getMdp(), co);
			reponse.data= compteValide;
			reponse.message= compteValide.insertToken(co);
			reponse.code="200";
		}
		catch(Exception ex) {
			reponse.code="400";
			reponse.message= ex.getMessage();
		}
		finally {
			if(co != null) co.close();
		}
		return reponse;
	}
	@PostMapping(value="/getIdCompte")
	public Response getId(@RequestBody String token) throws Exception {
		Connection co= new ConnectionPstg().getConnection();
		Response reponse= new Response();
		try {
			reponse.code="200";
			reponse.message=null;
			String idCompte= Compte.verificationToken(token,co);
			reponse.data= idCompte;
		}
		catch(Exception ex) {
			reponse.code="400";
			reponse.message= ex.getMessage();
		}
		finally {
			if(co != null) co.close();
		}
		return reponse;
	}
	
}
