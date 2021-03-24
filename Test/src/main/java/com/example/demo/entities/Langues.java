package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Langues implements Serializable {
	@Id
	@GeneratedValue
	int id_langue;
	
	@ManyToOne
	@JoinColumn(name = "id_cv")
	private CV cv;
	
	private String langue;
	private String niv_langue;

	public Langues() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Langues(CV cv, String langue, String niv_langue) {
		super();
		this.cv = cv;
		this.langue = langue;
		this.niv_langue = niv_langue;
	}

	public CV getCv() {
		return cv;
	}

	public void setCv(CV cv) {
		this.cv = cv;
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	public String getNiv_langue() {
		return niv_langue;
	}

	public void setNiv_langue(String niv_langue) {
		this.niv_langue = niv_langue;
	}

}
