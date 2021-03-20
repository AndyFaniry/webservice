package com.mobilemoney.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.mobilemoney.bdb.ConnectionPstg;

public class MouvementMoney {
	int idMobileMoney;
	int idOperateur;
	String nom;
	int idCompte;
	String num;
	Double valeur;
	LocalDateTime daty;
	int statu;

	public int getIdMobileMoney() {
		return idMobileMoney;
	}
	public void setIdMobileMoney(int idMobileMoney) {
		this.idMobileMoney = idMobileMoney;
	}
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
	public int getIdCompte() {
		return idCompte;
	}
	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public Double getValeur() {
		return valeur;
	}
	public void setValeur(Double valeur) {
		this.valeur = valeur;
	}
	public LocalDateTime getDaty() {
		return daty;
	}
	public void setDaty(LocalDateTime daty) {
		this.daty = daty;
	}
	public int getStatu() {
		return statu;
	}
	public void setStatu(int statu) {
		this.statu = statu;
	}
	public MouvementMoney() {}
	public MouvementMoney(int idMobileMoney, int idOperateur, String nom, int idCompte, String num, Double valeur,
			LocalDateTime daty, int statu) {
		setIdMobileMoney(idMobileMoney);
		setIdOperateur(idOperateur);
		setNom(nom);
		setIdCompte(idCompte);
		setNum(num);
		setValeur(valeur);
		setDaty(daty);
		setStatu(statu);
		
	}
	public MouvementMoney(int idMobileMoney,int idCompte,Double valeur,LocalDateTime daty, int statu) {
		setIdMobileMoney(idMobileMoney);
		setIdCompte(idCompte);
		setValeur(valeur);
		setDaty(daty);
		setStatu(statu);
		
	}
	public static ArrayList<MouvementMoney> findMobileMoney(String sql,Connection co){
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<MouvementMoney> mouvs=new ArrayList<MouvementMoney>();
		try {
			preparedStatement = co.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int idMouv= resultSet.getInt("idmobilemoney");
				int idCompte= resultSet.getInt("idcompte");
				Double valeur= resultSet.getDouble("valeur");
				LocalDateTime daty= resultSet.getTimestamp("daty").toLocalDateTime();
				int statu= resultSet.getInt("statu");
				MouvementMoney mouv=new MouvementMoney(idMouv,idCompte,valeur,daty,statu);
				mouvs.add(mouv);
			}
		}catch(Exception e) {
			e.getMessage();
		}
		return mouvs;
    }
	public static ArrayList<MouvementMoney> findMouvementMoney(String sql,Connection co){
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<MouvementMoney> mouvs=new ArrayList<MouvementMoney>();
		try {
			preparedStatement = co.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int idMouv= resultSet.getInt("idmobilemoney");
				int idOperateur= resultSet.getInt("idoperateur");
				String nom= resultSet.getString("nom");
				int idCompte= resultSet.getInt("idcompte");
				String num= resultSet.getString("num");
				Double valeur= resultSet.getDouble("valeur");
				LocalDateTime daty= resultSet.getTimestamp("daty").toLocalDateTime();
				int statu= resultSet.getInt("statu");
				MouvementMoney mouv=new MouvementMoney(idMouv,idOperateur,nom,idCompte,num,valeur,daty,statu);
				mouvs.add(mouv);
			}
		}catch(Exception e) {
			e.getMessage();
		}
		return mouvs;
    }
	public static void upDateMouvementMoney(int idMouv,Connection co) throws Exception {
		PreparedStatement st = null;
		try {
			boolean b=true;
			//this.checkClient(this.getIdClient())
			if(b) {
				String sql= "update mobileMoney set statu=1  where idMobileMoney=?";
				st = co.prepareStatement(sql);
				st.setInt(1,idMouv);
				st.execute();
				co.commit();
			}
			else throw new Exception("vous n'est pas parmis nos client");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(st != null) st.close();
		}
	}
	public static ArrayList<MouvementMoney> findMouvementMoneyById(int idMouv,Connection co){
		String sql= "select * from v_MobileMoney where idMobileMoney="+idMouv;
		ArrayList<MouvementMoney> mouvs= MouvementMoney.findMouvementMoney(sql, co);
		return mouvs;
    }
	public static void insertMouvement(int idCompte,String valeur,Connection co) throws Exception{
		Double val= new Double(valeur);
		PreparedStatement st = null;
		try {
			String sql= "insert into mobileMoney (idMobileMoney,idCompte,valeur,daty,statu) values (nextval('seqMobileMoney'),?,?,CURRENT_TIMESTAMP,0)";
			st = co.prepareStatement(sql);
			st.setInt(1,idCompte);
			st.setDouble(2,val);
			st.execute();
				co.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(st != null) st.close();
		}
	}
	public static ArrayList<MouvementMoney> getLastMouvBYCompte(int idCompte,Connection co){
		String sql= "select * from v_MobileMoney where idCompte="+idCompte+" order by daty desc limit 1";
		ArrayList<MouvementMoney> mouvs= MouvementMoney.findMouvementMoney(sql, co);
		return mouvs;
    }
	
	
	
	
}
