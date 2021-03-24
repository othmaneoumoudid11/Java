package com.example.demo.entities;

import java.io.Serializable;
import java.time.LocalDate;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Experiences implements Serializable {
	@Id
	@GeneratedValue
	int id_exp;
	
	@ManyToOne
	@JoinColumn(name = "id_cv")
	private CV cv;
	
	private String Experience;
	private LocalDate date_debut;
	private LocalDate date_fin;
	private String desc_exp;

	public Experiences() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Experiences(CV cv, String experience, LocalDate date_debut, LocalDate date_fin, String desc_exp) {
		super();
		this.cv = cv;
		Experience = experience;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.desc_exp = desc_exp;
	}

	public CV getCv() {
		return cv;
	}

	public void setCv(CV cv) {
		this.cv = cv;
	}

	public String getExperience() {
		return Experience;
	}

	public void setExperience(String experience) {
		Experience = experience;
	}

	public LocalDate getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(LocalDate date_debut) {
		this.date_debut = date_debut;
	}

	public LocalDate getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(LocalDate date_fin) {
		this.date_fin = date_fin;
	}

	public String getDesc_exp() {
		return desc_exp;
	}

	public void setDesc_exp(String desc_exp) {
		this.desc_exp = desc_exp;
	}

}
