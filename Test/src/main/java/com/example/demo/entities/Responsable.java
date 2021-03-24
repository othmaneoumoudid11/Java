package com.example.demo.entities;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Responsable implements Serializable {
@Id
@GeneratedValue
int id_res;

private String mail_res;
private String modt_res;
private String nom_res;
private String prenom_res;
private String cin_res;
private String telephone_res;


public Responsable() {
	super();
}


public Responsable(String mail_res, String modt_res, String nom_res, String prenom_res, String cin_res,
		String telephone_res) {
	super();
	this.mail_res = mail_res;
	this.modt_res = modt_res;
	this.nom_res = nom_res;
	this.prenom_res = prenom_res;
	this.cin_res = cin_res;
	this.telephone_res = telephone_res;
}


public String getMail_res() {
	return mail_res;
}


public void setMail_res(String mail_res) {
	this.mail_res = mail_res;
}


public String getModt_res() {
	return modt_res;
}


public void setModt_res(String modt_res) {
	this.modt_res = modt_res;
}


public String getNom_res() {
	return nom_res;
}


public void setNom_res(String nom_res) {
	this.nom_res = nom_res;
}


public String getPrenom_res() {
	return prenom_res;
}


public void setPrenom_res(String prenom_res) {
	this.prenom_res = prenom_res;
}


public String getCin_res() {
	return cin_res;
}


public void setCin_res(String cin_res) {
	this.cin_res = cin_res;
}


public String getTelephone_res() {
	return telephone_res;
}


public void setTelephone_res(String telephone_res) {
	this.telephone_res = telephone_res;
}


public int getId_res() {
	return id_res;
}


		
		
		
}
