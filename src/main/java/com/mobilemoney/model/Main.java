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
			String token="0bdafb21ea38b3921386707892692500";
			String daty1="2021-03-18";
			String daty2="2021-03-20";
			ArrayList<MouvementMoney> depot= Operateur.getDepotEffectuer(token,daty1,daty2,co);
			for(int i=0; i<depot.size(); i++) {
				System.out.println("size= "+((MouvementMoney)depot.get(i)).getValeur());
			}
			
		}
		catch(Exception ex) {
			retour= ex.getMessage();
		}finally {
			if(co != null) co.close();
			System.out.println(retour);
		}
		

	}
}
