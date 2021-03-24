package com.example.demo.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class LettreMotivation implements Serializable {

	@Id
	@GeneratedValue
	int id_let;
	
	private String cont_let;
	
	@OneToOne
	@JoinColumn(name = "id_off")
	private Offre offre;
	
	@ManyToOne
	@JoinColumn(name = "id_etd")
	private Etudiant etudiant;
	
	private String path;
	

	public LettreMotivation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LettreMotivation(String cont_let, Offre offre, Etudiant etudiant, String path) {
		super();
		this.cont_let = cont_let;
		this.offre = offre;
		this.etudiant = etudiant;
		this.path = path;
	}

	public String getCont_let() {
		return cont_let;
	}

	public void setCont_let(String cont_let) {
		this.cont_let = cont_let;
	}

	public Offre getOffre() {
		return offre;
	}

	public void setOffre(Offre offre) {
		this.offre = offre;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getId_let() {
		return id_let;
	} 


	

}
