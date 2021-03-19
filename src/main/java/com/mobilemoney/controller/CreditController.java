package com.mobilemoney.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobilemoney.model.Credit;
import com.mobilemoney.model.Response;
@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
public class CreditController {
	@PostMapping(value="/credit/ajout")
	public Response createClient(@RequestBody Credit credit) throws Exception {
		return Credit.ajoutCreditWebService(credit);
	}
}
