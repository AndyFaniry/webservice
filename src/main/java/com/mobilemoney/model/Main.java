package com.mobilemoney.model;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mobilemoney.bdb.ConnectionPstg;
import com.mobilemoney.controller.ClientController;
import com.mobilemoney.model.offre.*;


public class Main {
	@Autowired
	public ClientRepository clientRepository;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Connection co= new ConnectionPstg().getConnection();
		String retour="debut";
		try {
			String token="7c81eb8f2a38f04b8b6820f7525f0231";
			//String idOperateur= Token.verificationTokenAdmin(token, co);
			String nom= "offre vaovao Be2";
			String code="*999*";
			String prix="200";
			String validite= "3";
			//Response r= Offre.insertOffre(token,nom,code, prix,validite);
			String sql="select * from detailOffreAppel";
			ArrayList<DetailsOffreAppel> offre=  DetailsOffreAppel.findAllDetailOffreAppel(sql,co);
			System.out.println(offre.size());
			
			//ArrayList<Offre> offre= findAllDetailOffreAppel(String sql,Connection co);
			
			
		}
		catch(Exception ex) {
			retour= ex.getMessage();
		}finally {
			if(co != null) co.close();
			System.out.println(retour);
		}
		

	}
}
