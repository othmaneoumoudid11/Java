package com.example.demo.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity

public class ProjetsReal implements Serializable {
	@Id
	@GeneratedValue
	int id_pro;
	
	@ManyToOne
	@JoinColumn(name = "id_cv")
	private CV cv;
	
	private String sujet_proj;
	private String desc_proj;
	private LocalDate date_proj;

	public ProjetsReal() {
		super();
	}

	public ProjetsReal(CV cv, String sujet_proj, String desc_proj, LocalDate date_proj) {
		super();
		this.cv = cv;
		this.sujet_proj = sujet_proj;
		this.desc_proj = desc_proj;
		this.date_proj = date_proj;
	}

	public CV getCv() {
		return cv;
	}

	public void setCv(CV cv) {
		this.cv = cv;
	}

	public String getSujet_proj() {
		return sujet_proj;
	}

	public void setSujet_proj(String sujet_proj) {
		this.sujet_proj = sujet_proj;
	}

	public String getDesc_proj() {
		return desc_proj;
	}

	public void setDesc_proj(String desc_proj) {
		this.desc_proj = desc_proj;
	}

	public LocalDate getDate_proj() {
		return date_proj;
	}

	public void setDate_proj(LocalDate date_proj) {
		this.date_proj = date_proj;
	}

}
