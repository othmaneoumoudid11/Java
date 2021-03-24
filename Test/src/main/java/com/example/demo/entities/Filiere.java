package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Filiere {
	@Id
	@GeneratedValue
	int id_filiere;
	
	@ManyToOne
	@JoinColumn(name = "id_eco")
	private Ecole ecole;
	
	String filiere ;
	String abreviation;
	
	public Filiere() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Filiere(Ecole ecole, String filiere, String abreviation) {
		super();
		this.ecole = ecole;
		this.filiere = filiere;
		this.abreviation = abreviation;
	}
	public Ecole getEcole() {
		return ecole;
	}
	public void setEcole(Ecole ecole) {
		this.ecole = ecole;
	}
	public String getFiliere() {
		return filiere;
	}
	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}
	public String getAbreviation() {
		return abreviation;
	}
	public void setAbreviation(String abreviation) {
		this.abreviation = abreviation;
	}
	public int getId_filiere() {
		return id_filiere;
	}
	
}
