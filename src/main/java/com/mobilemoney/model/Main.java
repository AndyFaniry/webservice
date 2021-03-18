package com.mobilemoney.model;

import java.sql.Connection;
import java.time.LocalDateTime;
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
				
		}
		catch(Exception ex) {
			retour= ex.getMessage();
		}finally {
			if(co != null) co.close();
			System.out.println(retour);
		}
		

	}
	

}
