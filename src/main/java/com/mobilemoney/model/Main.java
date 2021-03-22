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
			String idOffre="2";
			String nom= "offre vaovao Be2";
			String code="*999*";
			String prix="200";
			String validite= "3";
			String idOInternet="5";
			String mo="30";
			DetailsOffreInternet.upDateDetailsOffreInternet(idOInternet,mo,co);
			//ArrayList<Offre> off= Offre.getOffreById(Integer.parseInt(idOffre),co);
			//System.out.println("size= "+off.size());
			
		}
		catch(Exception ex) {
			retour= ex.getMessage();
		}finally {
			if(co != null) co.close();
			System.out.println(retour);
		}
		

	}
}
