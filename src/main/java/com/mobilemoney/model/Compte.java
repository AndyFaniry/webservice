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
	int idOperateur;
	public int getIdOperateur() {
		return idOperateur;
	}
	public void setIdOperateur(int idOperateur) {
		this.idOperateur = idOperateur;
	}
	String num;
	String mdp;

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
	public Compte(int idCompte, int idClient,int idOperateur, String num, String mdp) {
		setIdCompte(idCompte);
		setIdClient(idClient);
		setIdOperateur(idOperateur);
		setNum(num);
		setMdp(mdp);
	}
	public Compte(int idClient,int idOperateur, String num, String mdp) {
		setIdClient(idClient);
		setIdOperateur(idOperateur);
		setNum(num);
		setMdp(mdp);
	}
	public static ArrayList<Compte> findAllCompte(String sql,Connection co) throws Exception{
		PreparedStatement st = null;
		ResultSet result = null;
		ArrayList<Compte> array = new ArrayList<Compte>();
		try {
			st = co.prepareStatement(sql);
			result = st.executeQuery(); 
			while(result.next()) {
				int id=result.getInt("idcompte");
				int idC=result.getInt("idclient");
				int idOp=result.getInt("idoperateur");
				String num=result.getString("num");
				String mdp=result.getString("mdp");
				Compte c=new Compte(id,idC,idOp,num,mdp);
				System.out.println(c.getNum());
				array.add(c);
			}
		}catch(Exception e) {
			e.getMessage();
		}finally {
			if(st!=null) st.close();
		}
		return array;
    }
	public static Compte valideLogin(String num, String mdp, Connection co) throws Exception {
		String sql= "select * from Compte where num='"+num+"' and mdp=md5('@client123"+mdp+"')";
		ArrayList<Compte> comptes= Compte.findAllCompte(sql, co);
		if(comptes.size()!=1) throw new Exception("mot de passe ou numero non valide");
		return comptes.get(0);
	}
	public static Compte findCompteById(int id,Connection co) throws Exception {
		String sql= "select * from Compte where idcompte="+id;
		ArrayList<Compte> comptes= Compte.findAllCompte(sql, co);
		if(comptes.size()!=1) throw new Exception("compte invalide");
		co.close();
		return comptes.get(0);
	}
	public static Response getCompte(int id) throws Exception {
		Connection co=new ConnectionPstg().getConnection();
		Response res=new Response();
		try {
			res.data= Compte.findCompteById(id, co);
			res.message= "compte";
			res.code="200";
		} catch (Exception e) {
			res.code="400";
			res.message= e.getMessage();
		} finally {
			if(co != null) co.close();
		}
		return res;
	}
	public void insert(Connection co)throws Exception{
		PreparedStatement st = null;
		try {
				String sql= "insert into compte(idCompte,idClient,idoperateur,num,mdp) VALUES (nextval('seqCompte'),?,?,?,md5(?))";
				st = co.prepareStatement(sql);
				st.setInt(1,this.getIdClient());
				st.setInt(2, this.getIdOperateur());
				st.setString(3,this.getNum());
				st.setString(4,"@client123"+this.getMdp());
				st.execute();
				co.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(st != null) st.close();
		}
	}
	
	public Response insertCompte(String nm,String mail) throws Exception {
		Connection co=new ConnectionPstg().getConnection();
		Response res=new Response();
		try {
			Client cl=Client.findClient(nm, mail);
			if(cl!=null) {
				this.insert(co);
			}
			else {
				Client.InsertClient(nm, mail);
				this.insert(co);
			}
			res.data= this;
			res.message= "compte inserer";
			res.code="200";
		} catch (Exception e) {
			res.code="400";
			res.message= e.getMessage();
		} finally {
			if(co != null) co.close();
		}
		return res;
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
	public static Response depotMoney(String token,String valeur) throws Exception {
		Connection co= new ConnectionPstg().getConnection();
		Response reponse= new Response();
		try {
			int idCompte= Token.verificationToken(token,co);
			MouvementMoney.insertMouvement(idCompte,valeur,co);
			ArrayList<MouvementMoney> mouv= MouvementMoney.getLastMouvBYCompte(idCompte,co);
			reponse.data= mouv;
			reponse.message= "depot effectuer veuillez attendre la validation";
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
