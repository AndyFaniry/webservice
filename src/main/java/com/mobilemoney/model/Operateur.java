package com.mobilemoney.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.mobilemoney.bdb.ConnectionPstg;

public class Operateur {
	 int idOperateur;
	 String nom;
	 String prefixe;
	 String mdp;
	public int getIdOperateur() {
		return idOperateur;
	}
	public void setIdOperateur(int idOperateur) {
		this.idOperateur = idOperateur;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrefixe() {
		return prefixe;
	}
	public void setPrefixe(String prefixe) {
		this.prefixe = prefixe;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public Operateur() {}
	public Operateur(int idOperateur, String nom, String prefixe, String mdp) {
		setIdOperateur(idOperateur);
		setNom(nom);
		setPrefixe(prefixe);
		setMdp(mdp);
	}
	public Operateur(String token, String nom, String prefixe, String mdp) {
		setIdOperateur(idOperateur);
		setNom(nom);
		setPrefixe(prefixe);
		setMdp(mdp);
	}
	public static ArrayList<Operateur> findOperateur(String sql,Connection co){
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Operateur> op=new ArrayList<Operateur>();
		try {
			preparedStatement = co.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int idOperateur=resultSet.getInt("idOperateur");
				String nom=resultSet.getString("nom");
				String prefixe=resultSet.getString("prefixe");
				String mdp=resultSet.getString("mdp");
				Operateur operateur= new Operateur(idOperateur,nom,prefixe,mdp);
				op.add(operateur);
			}
		}catch(Exception e) {
			e.getMessage();
		}
		return op;
    }
	public static Operateur valideLogin(String nom, String mdp, Connection co) throws Exception {
		String sql= "select * from operateur where nom='"+nom+"' and mdp=md5('@admin123"+mdp+"')";
		ArrayList<Operateur> operateur= Operateur.findOperateur(sql, co);
		if(operateur.size()!=1) throw new Exception("mot de passe ou nom non valide");
		return operateur.get(0);
	}
	public Response login() throws Exception {
		Connection co= new ConnectionPstg().getConnection();
		Response reponse= new Response();
		try {
			Operateur opValide= Operateur.valideLogin(this.getNom(),this.getMdp(), co);
			reponse.data= opValide;
			reponse.message= Token.insertTokenAdmin(opValide,co);
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
	public static Response getDepotNonValide(String token) throws Exception{
		Connection co= new ConnectionPstg().getConnection();
		Response reponse= new Response();
		ArrayList<MouvementMoney> val=Operateur.getMouvDepotNonValide(token,co);
		try {
			reponse.data= val;
			reponse.message= null;
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
	public static ArrayList<MouvementMoney> getMouvDepotNonValide(String token,Connection co) throws Exception {
		ArrayList<MouvementMoney> val= new ArrayList<MouvementMoney>();
		String idOperateur= Token.verificationTokenAdmin(token,co);
		String sql="select * from v_depot_non_valide where idOperateur="+idOperateur;
		System.out.println("sql andramana="+sql);
		val= MouvementMoney.findMouvementMoney(sql,co);
		return val;
	}
	public static Response getRetraitNonValide(String token) throws Exception{
		Connection co= new ConnectionPstg().getConnection();
		Response reponse= new Response();
		ArrayList<MouvementMoney> val=Operateur.getMouvRetraitNonValide(token,co);
		try {
			reponse.data= val;
			reponse.message= null;
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
	public static ArrayList<MouvementMoney> getMouvRetraitNonValide(String token,Connection co) throws Exception {
		ArrayList<MouvementMoney> val= new ArrayList<MouvementMoney>();
		String idOperateur= Token.verificationTokenAdmin(token,co);
		String sql="select * from v_retrait_non_valide where idOperateur="+idOperateur;
		System.out.println("sql andramana="+sql);
		val= MouvementMoney.findMouvementMoney(sql,co);
		return val;
	}
	public static Response validerMouvement(int idMouvementMoney) throws Exception{
		Connection co= new ConnectionPstg().getConnection();
		Response reponse= new Response();
		MouvementMoney.upDateMouvementMoney(idMouvementMoney,co);
		ArrayList<MouvementMoney> mouvs= MouvementMoney.findMouvementMoneyById(idMouvementMoney,co);
		try {
			reponse.data= mouvs;
			reponse.message= "mouvement valider";
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
	public static Response validerMouvement(String token, String idMouv) throws Exception{
		Connection co= new ConnectionPstg().getConnection();
		Response reponse= new Response();
		int idMouvementMoney= Integer.parseInt(idMouv);
		String idAdmin= Token.verificationTokenAdmin(token, co);
		ArrayList<MouvementMoney> mouvs= new ArrayList<MouvementMoney>();
		
		try {
			if(idAdmin!=null) {
				MouvementMoney.upDateMouvementMoney(idMouvementMoney,co);
				mouvs= MouvementMoney.findMouvementMoneyById(idMouvementMoney,co);
				reponse.data= mouvs;
				reponse.message= "mouvement valider";
				reponse.code="200";
			}
			else {
				reponse.data= null;
				reponse.message= "veuillez vous connecter";
				reponse.code="200";
			}
			
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
