package com.mobilemoney.model.offre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mobilemoney.bdb.ConnectionPstg;
import com.mobilemoney.model.Response;
import com.mobilemoney.model.Token;

	public class Offre {
		int idOffre;
		int idOperateur;
		String nom;
		String code;
		int prix;
		int validite;
		public int getIdOffre() {
			return idOffre;
		}
		public void setIdOffre(int idOffre) {
			this.idOffre = idOffre;
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
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public int getPrix() {
			return prix;
		}
		public void setPrix(int prix) {
			this.prix = prix;
		}
		public int getValidite() {
			return validite;
		}
		public void setValidite(int validite) {
			this.validite = validite;
		}
		public Offre(int idOffre, int idOperateur, String nom, String code, int prix, int validite) {
			setIdOffre(idOffre);
			setIdOperateur(idOperateur);
			setNom(nom);
			setCode(code);
			setPrix(prix);
			setValidite(validite);
		}
		public static ArrayList<Offre> findAllOffre(String sql,Connection co) throws Exception{
			PreparedStatement st = null;
			ResultSet result = null;
			ArrayList<Offre> array = new ArrayList<Offre>();
			try {
				st = co.prepareStatement(sql);
				result = st.executeQuery(); 
				while(result.next()) {
					int idOffre=result.getInt("idoffre");
					int idOperateur=result.getInt("idoperateur");
					String nom=result.getString("nom");
					String code=result.getString("code");
					int prix=result.getInt("prix");
					int validite=result.getInt("validite");
					Offre offre=new Offre(idOffre,idOperateur,nom,code,prix,validite);
					array.add(offre);
				}
			}catch(Exception e) {
				e.getMessage();
			}finally {
				if(st!=null) st.close();
			}
			return array;
	    }
		public static Response insertOffre(String token, String nom, String code, String prix, String validite) throws Exception {
			Connection co= new ConnectionPstg().getConnection();
			Response r= new Response();
			r.code= "200";
			r.data=null;
			String idOperateur= Token.verificationTokenAdmin(token, co);
			int idOp1= Integer.parseInt(idOperateur);
			int prix1= Integer.parseInt(prix);
			int validite1= Integer.parseInt(validite);
			PreparedStatement st = null;
			try {
				String sql= "insert into offre(idOffre,idOperateur,nom,code,prix,validite)VALUES (nextval('seqOffre'),?,?,?,?,?)";
				st = co.prepareStatement(sql);
				st.setInt(1,idOp1);
				st.setString(2,nom);
				st.setString(3,code);
				st.setInt(4,prix1);
				st.setInt(5,validite1);
				st.execute();
				co.commit();
				r.data=  getLastMouvBYCompte(idOp1,co);
				r.message= "la nouvelle offre est creer ajouter les details";
				
			} catch (Exception e) {
				r.code= "400";
				r.message= e.getMessage();
				e.printStackTrace();
			} finally {
				if(st != null) st.close();
				if(co!=null) co.close();
			}
			return  r;
		}
		public static ArrayList<Offre> getLastMouvBYCompte(int idOperateur,Connection co) throws Exception{
			String sql= "select * from offre where idOperateur="+idOperateur+" order by idOffre desc limit 1";
			ArrayList<Offre> mouvs= Offre.findAllOffre(sql, co);
			return mouvs;
	    }
		
	}
