package com.mobilemoney.model;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mobilemoney.bdb.ConnectionPstg;
import com.mobilemoney.controller.ClientController;


public class Main {
	@Autowired
	public ClientRepository clientRepository;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Connection co= new ConnectionPstg().getConnection();
		String retour="debut";
		try {
			String nom="telma";
			String mdp="telma";
			//Operateur op= Operateur.valideLogin(nom,mdp,co);
			//System.out.println(op.getPrefixe());
			String token="c24e6f2885b6affee1188f88fc98cf79";
			//Response r= Operateur.getDepotNonValide(token);
			//String sql="select * from v_depot_non_valide where idOperateur=1";
			ArrayList<MouvementMoney> aray= Operateur.getMouvDepot(token,co);
			System.out.println(aray.size());
			for(int i= 0; i<aray.size(); i++) {
				System.out.println(aray.get(i).getNum());
			}
			 //Response r= Operateur.getDepotNonValide(token);
			
			
		}
		catch(Exception ex) {
			retour= ex.getMessage();
		}finally {
			if(co != null) co.close();
			System.out.println(retour);
		}
		

	}
	

}
