package com.mobilemoney.controller;


import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.mobilemoney.model.offre.DetailsOffreAppel;
import com.mobilemoney.model.offre.DetailsOffreInternet;
import com.mobilemoney.model.offre.DetailsOffreSms;
import com.mobilemoney.model.offre.Offre;
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
	@GetMapping(value="/admin/depot")
	public Response listeDepot(@RequestHeader("Authorization") String bearertoken) throws Exception {
		String token= Token.deleteBearerToToken(bearertoken);
		return Operateur.getDepotNonValide(token);
	}
	@GetMapping(value="/admin/retrait")
	public Response listeRetrait(@RequestHeader("Authorization") String bearertoken) throws Exception {
		String token= Token.deleteBearerToToken(bearertoken);
		return Operateur.getDepotNonValide(token);
	}
	@PostMapping(value="/admin/valider/mouvement")
	public Response validerMouvement(@RequestHeader("Authorization") String bearertoken,@RequestBody Map<String,String> id) throws Exception {
		String token= Token.deleteBearerToToken(bearertoken);
		String idMouv= id.get("idMouvement");
		return Operateur.validerMouvement(token,idMouv);
	}
	@PostMapping(value="/admin/offre/ajout")
	public Response ajoutOffre(@RequestHeader("Authorization") String bearertoken,@RequestBody Map<String,String> donner) throws Exception {
		String token= Token.deleteBearerToToken(bearertoken);
		String nom= donner.get("nom");
		String code= donner.get("code");
		String prix= donner.get("prix");
		String validite= donner.get("validite");
		return Offre.insertOffre(token, nom, code, prix, validite);
	}
	@PostMapping(value="/admin/offre/appel/insert")
	public Response ajoutDetailOffreAppel(@RequestBody Map<String,String> donner) throws Exception {
		String idOffre= donner.get("idOffre");
		String valeurTTC= donner.get("valeurTTC");
		String puMemeOp= donner.get("puMemeOp");
		String puAutreOp= donner.get("puAutreOp");
		return DetailsOffreAppel.insertOffreAppel(idOffre,valeurTTC,puMemeOp,puAutreOp);
		
	}
	@PostMapping(value="/admin/offre/internet/insert")
	public Response ajoutDetailOffreInternet(@RequestBody Map<String,String> donner) throws Exception {
		String idOffre= donner.get("idOffre");
		String mo= donner.get("mo");
		return DetailsOffreInternet.insertOffreInternet(idOffre,mo);
		
	}
	@PostMapping(value="/admin/offre/sms/insert")
	public Response ajoutDetailOffreSms(@RequestBody Map<String,String> donner) throws Exception {
		String idOffre= donner.get("idOffre");
		String nbrSms= donner.get("nbrSms");
		return DetailsOffreSms.insertOffreSms(idOffre,nbrSms);
		
	}
	
}
