package com.mobilemoney.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="client")
public class Client {
	@Id
	String id;
	String nom;
	String email;
	public Client(String id, String nom, String email) {
		super();
		this.id = id;
		this.nom = nom;
		this.email = email;
	}
	public Client() {}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	/*public Boolean checkClient(int idC) {
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
    }*/
	public static ArrayList<Client> getListeClien(){
		ArrayList<Client> cl= new ArrayList<Client>();
		return cl;
	}
	
}
