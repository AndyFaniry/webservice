package com.mobilemoney.controller;


import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	@GetMapping(value="/admin/offre")
	public Response getListeOffre(@RequestHeader("Authorization") String bearertoken) throws Exception {
		String token= Token.deleteBearerToToken(bearertoken);
		return Offre.getListeOffre(token);
	}
	@GetMapping(value="/admin/offre/details")
	public Response getDetailsOffre(@RequestHeader("Authorization") String bearertoken,@RequestBody Map<String,String> donner) throws Exception {
		String token= Token.deleteBearerToToken(bearertoken);
		String idOffre= donner.get("idOffre");
		return Offre.getDetailsOffre(idOffre);
	}
	@DeleteMapping(value="/admin/offre/appel")
	public Response deleteDetailOffreAppel(@RequestBody Map<String,String> donner) throws Exception {
		String idOffre= donner.get("idOffre");
		return DetailsOffreAppel.deleteOffreAppel(idOffre);
	}
	@DeleteMapping(value="/admin/offre/internet")
	public Response deleteDetailOffreInternet(@RequestBody Map<String,String> donner) throws Exception {
		String idOffre= donner.get("idOffre");
		return DetailsOffreInternet.deleteOffreInternet(idOffre);
	}
	@DeleteMapping(value="/admin/offre")
	public Response deleteDetailOffreSms(@RequestBody Map<String,String> donner) throws Exception {
		String idOffre= donner.get("idOffre");
		return Offre.deleteOffre(idOffre);
	}
	@PutMapping(value="/admin/offre")
	public Response updateDetailOffre(@RequestBody Map<String,String> donner) throws Exception {
		String idOffre= donner.get("idOffre");
		String nom= donner.get("nom");
		String code= donner.get("code");
		String prix= donner.get("prix");
		String validite= donner.get("validite");
		return Offre.updateOffre(idOffre, nom,code,prix,validite);
	}
	@PutMapping(value="/admin/offre/appel")
	public Response updateDetailOffreAppel(@RequestBody Map<String,String> donner) throws Exception {
		String idOAppel= donner.get("idOAppel");
		String valeurTTC= donner.get("valeurTTC");
		String puMemeOp= donner.get("puMemeOp");
		String puAutreOp= donner.get("puAutreOp");
		return DetailsOffreAppel.updateDetailsOffreAppel(idOAppel,valeurTTC,puMemeOp,puAutreOp);
	}
	@PutMapping(value="/admin/offre/internet")
	public Response updateDetailOffreinternet(@RequestBody Map<String,String> donner) throws Exception {
		String idOInternet= donner.get("idOInternet");
		String mo= donner.get("mo");
		return DetailsOffreInternet.updateDetailsOffreInternet(idOInternet,mo);
	}
	@PutMapping(value="/admin/offre/sms")
	public Response updateDetailOffreSms(@RequestBody Map<String,String> donner) throws Exception {
		String idOSms= donner.get("idOSms");
		String nbrSms= donner.get("nbrSms");
		return DetailsOffreSms.updateDetailsOffreSms(idOSms,nbrSms);
	}
	@GetMapping(value="/admin/stat/depot")
	public Response satistiqueDepot(@RequestHeader("Authorization") String bearertoken,@RequestBody Map<String,String> donner) throws Exception {
		String token= Token.deleteBearerToToken(bearertoken);
		String daty1= donner.get("daty1");
		String daty2= donner.get("daty2");
		return Operateur.statDepot(token,"depot",daty1,daty2);
	}
	@GetMapping(value="/admin/stat/retrait")
	public Response satistiqueRetrait(@RequestHeader("Authorization") String bearertoken,@RequestBody Map<String,String> donner) throws Exception {
		String token= Token.deleteBearerToToken(bearertoken);
		String daty1= donner.get("daty1");
		String daty2= donner.get("daty2");
		return Operateur.statDepot(token,"retrait",daty1,daty2);
	}
	@GetMapping(value="/admin/operateur")
	public Response getOperateur(@RequestHeader("Authorization") String bearertoken) throws Exception {
		String token= Token.deleteBearerToToken(bearertoken);
		return Operateur.getOperateur(token);
	}
	@PostMapping(value="/admin/check")
	public Response check(@RequestHeader("Authorization") String bearertoken,@RequestBody Map<String,String> mdp) throws Exception {
		String token= Token.deleteBearerToToken(bearertoken);
		String md=mdp.get("mdp");
		return Operateur.checkMdp(token, md);
	}
}

