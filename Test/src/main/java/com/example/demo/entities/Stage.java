package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Stage implements Serializable {

	@Id
	@GeneratedValue
	int id_stage;

	@OneToOne
	@JoinColumn(name = "id_off")
	private Offre offre;

	@ManyToOne
	@JoinColumn(name = "id_etd")
	private Etudiant etudiant;

	@ManyToOne
	@JoinColumn(name = "id_entr")
	private Entreprise entreprise;

	private double note_stage;

	public Stage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Stage(Offre offre, Etudiant etudiant, Entreprise entreprise, double note_stage) {
		super();
		this.offre = offre;
		this.etudiant = etudiant;
		this.entreprise = entreprise;
		this.note_stage = note_stage;
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

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public double getNote_stage() {
		return note_stage;
	}

	public void setNote_stage(double note_stage) {
		this.note_stage = note_stage;
	}

	public int getId_stage() {
		return id_stage;
	}

}
