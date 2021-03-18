package com.mobilemoney.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import com.mobilemoney.bdb.ConnectionPstg;
import com.mobilemoney.fonction.Fonction;

public class Compte {
	int idCompte;
	int idClient;
	String num;
	String mdp;
	@Autowired
	public ClientRepository clientRepository;
	public int getIdCompte() {
		return idCompte;
	}
	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public Compte() {}
	public Compte(int idCompte, int idClient, String num, String mdp) {
		super();
		this.idCompte = idCompte;
		this.idClient = idClient;
		this.num = num;
		this.mdp = mdp;
	}
	public Compte(int idClient, String num, String mdp) {
		super();
		this.idClient = idClient;
		this.num = num;
		this.mdp = mdp;
	}
	public static ArrayList<Compte> findAllCompte(String sql,Connection co){
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Compte> cpt=new ArrayList<Compte>();
		try {
			preparedStatement = co.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id=resultSet.getInt("idCompte");
				int idC=resultSet.getInt("idClient");
				String num=resultSet.getString("num");
				String mdp=resultSet.getString("mdp");
				Compte c=new Compte(id,idC,num,mdp);
				cpt.add(c);
			}
		}catch(Exception e) {
			e.getMessage();
		}
		return cpt;
    }
	public static Compte valideLogin(String num, String mdp, Connection co) throws Exception {
		String sql= "select * from Compte where num='"+num+"' and mdp=md5('"+mdp+"')";
		ArrayList<Compte> comptes= Compte.findAllCompte(sql, co);
		if(comptes.size()!=1) throw new Exception("mot de passe ou numero non valide");
		return comptes.get(0);
	}
	
	public Compte insertCompte(Connection co) throws Exception {
		PreparedStatement st = null;
		try {
			boolean b=true;
			//this.checkClient(this.getIdClient())
			if(b) {
				String sql= "insert into compte(idCompte,idClient,num,mdp) VALUES (nextval('seqCompte'),?,?,md5(?))";
				st = co.prepareStatement(sql);
				st.setInt(1,this.getIdClient());
				st.setString(2,this.getNum());
				st.setString(3,this.getMdp());
				st.execute();
				co.commit();
			}
			else throw new Exception("vous n'est pas parmis nos client");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(st != null) st.close();
		}
		return this;
	}
	public Response login() throws Exception {
		Connection co= new ConnectionPstg().getConnection();
		Response reponse= new Response();
		try {
			Compte compteValide= Compte.valideLogin(this.getNum(),this.getMdp(), co);
			reponse.data= compteValide;
			reponse.message= Token.insertToken(compteValide,co);
			reponse.code="200";
		}
		catch(Exception ex) {
			reponse.code="400";
			reponse.message= ex.getMessage();
		}
		finally {
			if(co != null) co.close();
		}
		return reponse;
	}
	

}
