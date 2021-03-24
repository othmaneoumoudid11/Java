package com.example.demo.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Ecole implements Serializable {
	@Id
	@GeneratedValue
	int id_eco;
	
	@OneToOne
	@JoinColumn(name = "id_res")
	private Responsable responsable;
	
	@OneToOne
	@JoinColumn(name = "id_comp")
	private Compte compte;
	
	public Collection<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(Collection<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	public Collection<Convention> getConventions() {
		return conventions;
	}

	public void setConventions(Collection<Convention> conventions) {
		this.conventions = conventions;
	}
	
	

	public int getId_eco() {
		return id_eco;
	}

	private String nom_ecole;
	private String ville_eco;
	private String mail_eco;
	private String tele_eco;
	private String addr_eco;
	
	

	@OneToMany(targetEntity = Etudiant.class, cascade = CascadeType.ALL, mappedBy = "ecole", fetch = FetchType.LAZY)
	private Collection<Etudiant> etudiants;

	@OneToMany(targetEntity = Convention.class, cascade = CascadeType.ALL, mappedBy = "ecole", fetch = FetchType.LAZY)
	private Collection<Convention> conventions;

	
	
	
	public Ecole() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ecole(Compte compte, String nom_ecole, String ville_eco, String mail_eco, String tele_eco, String addr_eco, Responsable responsable) {
		super();
		this.compte = compte;
		this.nom_ecole = nom_ecole;
		this.ville_eco = ville_eco;
		this.mail_eco = mail_eco;
		this.tele_eco = tele_eco;
		this.addr_eco = addr_eco;
		this.responsable=responsable;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public String getNom_ecole() {
		return nom_ecole;
	}

	public void setNom_ecole(String nom_ecole) {
		this.nom_ecole = nom_ecole;
	}

	public String getVille_eco() {
		return ville_eco;
	}

	public void setVille_eco(String ville_eco) {
		this.ville_eco = ville_eco;
	}

	public String getMail_eco() {
		return mail_eco;
	}

	public void setMail_eco(String mail_eco) {
		this.mail_eco = mail_eco;
	}

	public String getTele_eco() {
		return tele_eco;
	}

	public void setTele_eco(String tele_eco) {
		this.tele_eco = tele_eco;
	}

	public String getAddr_eco() {
		return addr_eco;
	}

	public void setAddr_eco(String addr_eco) {
		this.addr_eco = addr_eco;
	}

	public Responsable getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}
	
	

}
