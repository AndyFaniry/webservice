package com.mobilemoney.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobilemoney.model.Compte;
import com.mobilemoney.model.Response;


@RestController
public class CompteController {
	@PostMapping(value="/compte/login")
	public Response loginClient(@RequestBody Compte compte) throws Exception {
		return compte.login();
	}
	
}
