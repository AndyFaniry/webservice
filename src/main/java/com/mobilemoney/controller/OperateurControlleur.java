package com.mobilemoney.controller;


import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.mobilemoney.model.Operateur;
import com.mobilemoney.model.Response;
import com.mobilemoney.model.Token;

@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
public class OperateurControlleur {
	@PostMapping(value="/admin/login")
	public Response loginAdmin(@RequestBody Operateur op) throws Exception {
		return op.login();
	}
	@PostMapping(value="/admin/depot")
	public Response listeDepot(@RequestHeader("Authorization") String bearertoken) throws Exception {
		String token= Token.deleteBearerToToken(bearertoken);
		return Operateur.getDepotNonValide(token);
	}
	@PostMapping(value="/admin/retrait")
	public Response listeRetrait(@RequestHeader("Authorization") String bearertoken) throws Exception {
		String token= Token.deleteBearerToToken(bearertoken);
		return Operateur.getDepotNonValide(token);
	}
	@PostMapping(value="/admin/valider")
	public Response validerMouvement(@RequestBody int idMouv) throws Exception {
		return Operateur.validerMouvement(idMouv);
	}
	@PostMapping(value="/admin/valider/mouvement")
	public Response validerMouvement1(@RequestHeader("Authorization") String bearertoken,@RequestBody Map<String,String> id) throws Exception {
		String token= Token.deleteBearerToToken(bearertoken);
		String idMouv= id.get("idMouvement");
		return Operateur.validerMouvement(token,idMouv);
	}
	
}
