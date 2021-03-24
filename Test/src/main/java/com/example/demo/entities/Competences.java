package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Competences {
	@Id
	@GeneratedValue
	int id_com;
	
	@ManyToOne
	@JoinColumn(name = "id_cv")
	private CV cv;
	
	private String competence;

	public Competences() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Competences(CV cv, String competence) {
		super();
		this.cv = cv;
		this.competence = competence;
	}

	public CV getCv() {
		return cv;
	}

	public void setCv(CV cv) {
		this.cv = cv;
	}

	public String getCompetence() {
		return competence;
	}

	public void setCompetence(String competence) {
		this.competence = competence;
	}

}
