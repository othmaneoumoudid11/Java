package com.example.demo.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity

public class Formation implements Serializable {
	@Id
	@GeneratedValue
	int id_for;
	
	@ManyToOne
	@JoinColumn(name = "id_cv")
	private CV cv;
	
	private String formation;
	private String source;
	private LocalDate date_debut;
	private LocalDate date_fin;
	
	public Formation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Formation( CV cv, String formation, String source, LocalDate date_debut, LocalDate date_fin) {
		super();
		this.cv = cv;
		this.formation = formation;
		this.source = source;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
	}


	public CV getCv() {
		return cv;
	}
	
	
	
	
	public void setCv(CV cv) {
		this.cv = cv;
	}
	public String getFormation() {
		return formation;
	}
	public void setFormation(String formation) {
		this.formation = formation;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
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

	public int getId_for() {
		return id_for;
	}
	
	
}
