package com.example.demo.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class CV implements Serializable {
	@Id
	@GeneratedValue
	int Id_cv;
	
	@OneToOne
	@JoinColumn(name = "id_etd")
	private Etudiant etudiant;
	
	private String linkedin;
	private String Github;
	private String Info_cv;

	@OneToMany(mappedBy = "cv")
	private Collection<Hobbies> hobbies;
	@OneToMany(mappedBy = "cv")
	private Collection<Experiences> experiences;
	@OneToMany(mappedBy = "cv")
	private Collection<Langues> langues;
	@OneToMany(mappedBy = "cv")
	private Collection<ProjetsReal> projetsReals;

	@OneToMany(mappedBy = "cv")
	private Collection<Formation> formations;

	public CV() {
		super();
	}

	public CV(Etudiant etudiant, String linkedin, String github, String info_cv) {
		super();
		this.etudiant = etudiant;
	
		this.linkedin = linkedin;
		Github = github;
		Info_cv = info_cv;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public String getGithub() {
		return Github;
	}

	public void setGithub(String github) {
		Github = github;
	}

	public String getInfo_cv() {
		return Info_cv;
	}

	public void setInfo_cv(String info_cv) {
		Info_cv = info_cv;
	}

	public Collection<Hobbies> getHobbies() {
		return hobbies;
	}

	public void setHobbies(Collection<Hobbies> hobbies) {
		this.hobbies = hobbies;
	}

	public Collection<Experiences> getExperiences() {
		return experiences;
	}

	public void setExperiences(Collection<Experiences> experiences) {
		this.experiences = experiences;
	}

	public Collection<Langues> getLangues() {
		return langues;
	}

	public void setLangues(Collection<Langues> langues) {
		this.langues = langues;
	}

	public Collection<ProjetsReal> getProjetsReals() {
		return projetsReals;
	}

	public void setProjetsReals(Collection<ProjetsReal> projetsReals) {
		this.projetsReals = projetsReals;
	}

	public int getId_cv() {
		return Id_cv;
	}

	public Collection<Formation> getFormations() {
		return formations;
	}

	public void setFormations(Collection<Formation> formations) {
		this.formations = formations;
	}

}
