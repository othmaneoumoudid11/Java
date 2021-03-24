package com.example.demo.entities;

import java.io.Serializable;
import java.nio.MappedByteBuffer;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Entreprise implements Serializable {
	@Id
	@GeneratedValue
	int id_entr;
	
	
	private String nom_entr;
	private String dom_entr;
	private String addr_entr;
	private LocalDate date_entr;
	private String tele_fix_entr;

	@OneToOne
	@JoinColumn(name = "id_comp")
	private Compte compte;
	
	
	@OneToOne
	@JoinColumn(name = "id_res")
	private Responsable responsable;
	
	

	@ManyToMany(mappedBy = "entreprise")
	private Collection<Offre> offres;

	@OneToMany(targetEntity = Entretien.class, cascade = CascadeType.ALL, mappedBy = "entreprise", fetch = FetchType.LAZY)
	private Collection<Entretien> entretiens;

	@OneToMany(targetEntity = Stage.class, cascade = CascadeType.ALL, mappedBy = "entreprise", fetch = FetchType.LAZY)
	private Collection<Stage> stages;

	public Entreprise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Entreprise(String nom_entr, String dom_entr, String addr_entr, LocalDate date_entr, String tele_fix_entr,
			Compte compte,Responsable responsable) {
		super();
		this.nom_entr = nom_entr;
		this.dom_entr = dom_entr;
		this.addr_entr = addr_entr;
		this.date_entr = date_entr;
		this.tele_fix_entr = tele_fix_entr;
		this.compte = compte;
		this.responsable=responsable;
	}

	public String getNom_entr() {
		return nom_entr;
	}

	public void setNom_entr(String nom_entr) {
		this.nom_entr = nom_entr;
	}

	public String getDom_entr() {
		return dom_entr;
	}

	public int getId_entr() {
		return id_entr;
	}

	public void setDom_entr(String dom_entr) {
		this.dom_entr = dom_entr;
	}

	public String getAddr_entr() {
		return addr_entr;
	}

	public void setAddr_entr(String addr_entr) {
		this.addr_entr = addr_entr;
	}

	public LocalDate getDate_entr() {
		return date_entr;
	}

	public void setDate_entr(LocalDate date_entr) {
		this.date_entr = date_entr;
	}

	public String getTele_fix_entr() {
		return tele_fix_entr;
	}

	public void setTele_fix_entr(String tele_fix_entr) {
		this.tele_fix_entr = tele_fix_entr;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public Responsable getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}

	public Collection<Offre> getOffres() {
		return offres;
	}

	public void setOffres(Collection<Offre> offres) {
		this.offres = offres;
	}

	public Collection<Entretien> getEntretiens() {
		return entretiens;
	}

	public void setEntretiens(Collection<Entretien> entretiens) {
		this.entretiens = entretiens;
	}

	public Collection<Stage> getStages() {
		return stages;
	}

	public void setStages(Collection<Stage> stages) {
		this.stages = stages;
	}

	
	
	
}
