package com.mobilemoney.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.mobilemoney.model.Compte;
import com.mobilemoney.model.MouvementMoney;
import com.mobilemoney.model.Response;
import com.mobilemoney.model.Token;

@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
public class CompteController {
	@PostMapping(value="/compte/login")
	public Response loginClient(@RequestBody Compte compte) throws Exception {
		return compte.login();
	}
	@PostMapping(value="/compte/depot")
	public Response depot(@RequestHeader("Authorization") String bearertoken,@RequestBody Map<String,String> valeur) throws Exception {
		String token= Token.deleteBearerToToken(bearertoken);
		String valeur1= valeur.get("valeur");
		Response r= Compte.depotMoney(token,valeur1);
		return r;
	}
}
