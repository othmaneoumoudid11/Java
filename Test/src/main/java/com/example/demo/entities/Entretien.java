package com.example.demo.entities;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Entretien {
	@Id
	@GeneratedValue
	int id_ent;
	
	@ManyToOne
	@JoinColumn(name = "id_off")
	private Offre offre;

	@ManyToOne
	@JoinColumn(name = "id_etd")
	private Etudiant etudiant;

	@ManyToOne
	@JoinColumn(name = "id_entr")
	private Entreprise entreprise;
	
	private LocalDateTime date_entr;

	private int val_cand;
	private int ref_entr;

	public Entretien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Entretien(Offre offre, Etudiant etudiant, Entreprise entreprise, LocalDateTime date_entr) {
		super();
		this.offre = offre;
		this.etudiant = etudiant;
		this.entreprise = entreprise;
		this.date_entr = date_entr;
		this.val_cand = 2;
		this.ref_entr = 2;
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

	public LocalDateTime getDate_entr() {
		return date_entr;
		
	}

	public void setDate_entr(LocalDateTime date_entr) {
		this.date_entr = date_entr;
	}

	public int isVal_cand() {
		return val_cand;
	}

	public void setVal_cand(int val_cand) {
		this.val_cand = val_cand;
	}

	public int isRef_entr() {
		return ref_entr;
	}

	public void setRef_entr(int ref_entr) {
		this.ref_entr = ref_entr;
	}

	public int getId_ent() {
		return id_ent;
	}

	
	
}
