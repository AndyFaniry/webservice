package com.mobilemoney.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
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
	public String getToken(Connection co) throws Exception {
		String daty= Fonction.getDateNow(co);
		String mdp= this.getMdp();
		String val= daty+"@123"+mdp;
		String token= Fonction.addSha1(val, co);
		return token;
	}
	public String insertToken(Connection co) throws Exception {
		String token= this.getToken(co);
		PreparedStatement st = null;
		try {
			String sql= "insert into token(idToken,idCompte,valeur,daty,statu) VALUES (nextval('seqToken'),?,?,CURRENT_TIMESTAMP,1)";
			st = co.prepareStatement(sql);
			st.setInt(1,this.getIdCompte());
			st.setString(2,token);
			st.execute();
			co.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(st != null) st.close();
		}
		return token;
	}
	public static String verificationToken(String token,Connection co) throws Exception {
		String sql= "select * from token where valeur=? and statu=1";
		PreparedStatement st = null;
		ResultSet resultSet = null;
		String idCompte="fgh";
		try {
			st = co.prepareStatement(sql);
			st.setString(1,token);
			resultSet = st.executeQuery();
			while (resultSet.next()) {
				idCompte =resultSet.getString("idcompte");			
			}
		}catch(Exception e) {
			e.getMessage();
		}
		return idCompte;
	}
	public Compte insertCompte(Connection co) throws Exception {
		PreparedStatement st = null;
		try {
			if(this.checkClient(this.getIdClient())) {
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
	
	public Boolean checkClient(int idC) {
    	List<Client> client=this.clientRepository.findAll();
		int size=client.size();
		int i=0;
		boolean val=false;
		while(i<=size) {
			if(Integer.parseInt(client.get(i).id)==idClient) {
				val=true;
				break;
				}
			i++;
			if(i==size) {val=false;}
		}
		return val;
    }

}
