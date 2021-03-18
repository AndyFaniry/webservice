package com.mobilemoney.model;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

import com.mobilemoney.bdb.ConnectionPstg;


public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Connection co= new ConnectionPstg().getConnection();
		String retour="debut";
		try {
				LocalDateTime now=LocalDateTime.now();
				System.out.println(now);
				String d="2021-03-18T11:05:03.119131400";
				LocalDateTime n= LocalDateTime.parse(d);
				System.out.println(n.toString());
				Credit credit= new Credit(1,"12345678901234",2000,"2021-03-18T11:05:03.119131400");
				//Credit.mouvementCredit(credit,co);
				System.out.println("ok");
				String sql="select * from credit";
				List<Credit>  crds= Credit.findAllCredit(sql,co);
				System.out.println("nbr"+crds.size()+" voalohany= "+crds.get(0).getValeur());
		}
		catch(Exception ex) {
			retour= ex.getMessage();
		}finally {
			if(co != null) co.close();
			System.out.println(retour);
		}
		

	}
	

}
