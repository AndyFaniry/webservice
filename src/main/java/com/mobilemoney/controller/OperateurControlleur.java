package com.mobilemoney.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobilemoney.model.Operateur;
import com.mobilemoney.model.Response;

@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
public class OperateurControlleur {
	@PostMapping(value="/admin/login")
	public Response loginAdmin(@RequestBody Operateur op) throws Exception {
		return op.login();
	}
	@PostMapping(value="/admin/depot")
	public Response loginAdmin(@RequestBody String token) throws Exception {
		return Operateur.getDepotNonValide(token);
	}
	@PostMapping(value="/admin/test")
	public Response liste(@RequestBody String token) throws Exception {
		return Operateur.getDepotNonValide(token);
	}
}
