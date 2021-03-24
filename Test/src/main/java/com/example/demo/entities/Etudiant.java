package com.example.demo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Etudiant implements Serializable {
	@Id
	@GeneratedValue
	int id_etd;
	
	private String nom_etd;
	private String prenom_etd;
	private String mail_etd;
	private String tele_etd;
	private String dom_etd;
	private boolean stage_tr;
    
	@OneToOne
	@JoinColumn(name = "id_comp")
	private Compte compte;
	
	
	@OneToOne
	@JoinColumn(name = "id_filiere")
	private Filiere filiere;
	
	
	

	@ManyToOne
	@JoinColumn(name = "id_eco")
	private Ecole ecole;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Postuler", joinColumns = { @JoinColumn(name = "id_etd") }, inverseJoinColumns = {
			@JoinColumn(name = "id_off") })
	private Collection<Offre> offres = new ArrayList<>();

	@OneToMany(targetEntity = Convention.class, cascade = CascadeType.ALL, mappedBy = "etudiant", fetch = FetchType.LAZY)
	private Collection<Convention> conventions;

	@OneToMany(targetEntity = Entretien.class, cascade = CascadeType.ALL, mappedBy = "etudiant", fetch = FetchType.LAZY)
	private Collection<Entretien> entretiens;

	@OneToMany(targetEntity = Stage.class, cascade = CascadeType.ALL, mappedBy = "etudiant", fetch = FetchType.LAZY)
	private Collection<Stage> stages;

	@OneToMany(targetEntity = LettreMotivation.class, cascade = CascadeType.ALL, mappedBy = "etudiant", fetch = FetchType.LAZY)
	private Collection<LettreMotivation> lettres;

	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Etudiant(Ecole ecole, String nom_etd, String prenom_etd, String mail_etd, String tele_etd, String dom_etd,
			Filiere filiere, boolean stage_tr) {
		super();
		this.ecole = ecole;
		this.nom_etd = nom_etd;
		this.prenom_etd = prenom_etd;
		this.mail_etd = mail_etd;
		this.tele_etd = tele_etd;
		this.dom_etd = dom_etd;
		this.filiere = filiere;
		this.stage_tr = stage_tr;
		this.compte=null;
	}

	public int getId_etd() {
		return id_etd;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public Collection<Convention> getConventions() {
		return conventions;
	}

	public void setConventions(Collection<Convention> conventions) {
		this.conventions = conventions;
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

	public Collection<LettreMotivation> getLettres() {
		return lettres;
	}

	public void setLettres(Collection<LettreMotivation> lettres) {
		this.lettres = lettres;
	}

	public Ecole getEcole() {
		return ecole;
	}

	public void setEcole(Ecole ecole) {
		this.ecole = ecole;
	}

	public String getNom_etd() {
		return nom_etd;
	}

	public void setNom_etd(String nom_etd) {
		this.nom_etd = nom_etd;
	}

	public String getPrenom_etd() {
		return prenom_etd;
	}

	public void setPrenom_etd(String prenom_etd) {
		this.prenom_etd = prenom_etd;
	}

	public String getMail_etd() {
		return mail_etd;
	}

	public void setMail_etd(String mail_etd) {
		this.mail_etd = mail_etd;
	}

	public String getTele_etd() {
		return tele_etd;
	}

	public void setTele_etd(String tele_etd) {
		this.tele_etd = tele_etd;
	}

	public String getDom_etd() {
		return dom_etd;
	}

	public void setDom_etd(String dom_etd) {
		this.dom_etd = dom_etd;
	}

	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}
	

	public boolean isStage_tr() {
		return stage_tr;
	}

	public void setStage_tr(boolean stage_tr) {
		this.stage_tr = stage_tr;
	}



	public Collection<Offre> getOffres() {
		return offres;
	}

	public void setOffres(Collection<Offre> offres) {
		this.offres = offres;
	}

	public void setId_etd(int id_etd) {
		this.id_etd = id_etd;
	}

	
}