package com.mobilemoney.model.offre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mobilemoney.bdb.ConnectionPstg;
import com.mobilemoney.model.Response;

public class DetailsOffreSms {
	int idOInternet;
	int idOffre;
	int nbrSms;
	public int getIdOInternet() {
		return idOInternet;
	}
	public void setIdOInternet(int idOInternet) {
		this.idOInternet = idOInternet;
	}
	public int getIdOffre() {
		return idOffre;
	}
	public void setIdOffre(int idOffre) {
		this.idOffre = idOffre;
	}
	public int getNbrSms() {
		return nbrSms;
	}
	public void setNbrSms(int nbrSMS) {
		this.nbrSms = nbrSMS;
	}
	public DetailsOffreSms(int idOInternet, int idOffre, int nbrSMS) {
		super();
		setIdOInternet(idOInternet);
		setIdOffre(idOffre);
		setNbrSms(nbrSMS);
	}
	public static ArrayList<DetailsOffreSms> findAllDetailOffreSms(String sql,Connection co) throws Exception{
		PreparedStatement st = null;
		ResultSet result = null;
		ArrayList<DetailsOffreSms> array = new ArrayList<DetailsOffreSms>();
		try {
			st = co.prepareStatement(sql);
			result = st.executeQuery(); 
			while(result.next()) {
				int idDetailOffreSms=result.getInt("idOSms");
				int idOffre=result.getInt("idOffre");
				int nbrSms=result.getInt("nbrSms");
				DetailsOffreSms offre=new DetailsOffreSms(idDetailOffreSms,idOffre,nbrSms);
				array.add(offre);
			}
		}catch(Exception e) {
			e.getMessage();
		}finally {
			if(st!=null) st.close();
		}
		return array;
    }

	public static Response insertOffreSms(String idOffre, String nbrSms) throws Exception {
		Connection co= new ConnectionPstg().getConnection();
		Response r= new Response();
		r.code= "200";
		r.data=null;
		int idOffre1= Integer.parseInt(idOffre);
		int nbrSms1= Integer.parseInt(nbrSms);
		PreparedStatement st = null;
		try {
			String sql= "insert into detailOffreSms(idOSms, idOffre,nbrSms) VALUES (nextval('seqDetailsOffreInternet'),?,?)";
			st = co.prepareStatement(sql);
			st.setInt(1,idOffre1);
			st.setInt(2,nbrSms1);
			st.execute();
			co.commit();
			r.data= null ;
			r.message= "la nouvelle details offre Sms inserer";
			
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
	

}
