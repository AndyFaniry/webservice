package com.mobilemoney.model;

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

}
