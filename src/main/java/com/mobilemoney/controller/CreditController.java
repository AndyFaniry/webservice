package com.mobilemoney.controller;

import java.sql.Connection;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobilemoney.bdb.ConnectionPstg;
import com.mobilemoney.model.Credit;
import com.mobilemoney.model.Response;

@RestController
public class CreditController {
	@PostMapping(value="/AjoutCredit")
	public Response createClient(@RequestBody Credit credit) throws Exception {
		Connection co= new ConnectionPstg().getConnection();
		Response reponse= new Response();
		try {
			Credit.ajoutCredit(credit,co);
			Credit crd= Credit.getSoldeCredit(credit.getIdCompte(),co);
			reponse.data= crd;
			reponse.message= null;
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
}
