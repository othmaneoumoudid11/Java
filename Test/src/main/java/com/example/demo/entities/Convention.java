package com.example.demo.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Convention {
	@Id @GeneratedValue
	int id_conv;
	
	@ManyToOne
	@JoinColumn(name="id_etd")
	private Etudiant etudiant;
	
	@OneToOne
	@JoinColumn(name="id_off")
	private Offre offre;
    
	@ManyToOne
	@JoinColumn(name="id_eco")
	private Ecole ecole;
	
	private boolean accept;
	

	public boolean isAccept() {
		return accept;
	}

	public void setAccept(boolean accept) {
		this.accept = accept;
	}

	public Convention() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Convention(Etudiant etudiant, Offre offre, Ecole ecole) {
		super();
		this.etudiant = etudiant;
		this.offre = offre;
		this.ecole = ecole;
		
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public Offre getOffre() {
		return offre;
	}

	public void setOffre(Offre offre) {
		this.offre = offre;
	}

	public Ecole getEcole() {
		return ecole;
	}

	public void setEcole(Ecole ecole) {
		this.ecole = ecole;
	}

	

	public int getId_conv() {
		return id_conv;
	}

	
	
}
