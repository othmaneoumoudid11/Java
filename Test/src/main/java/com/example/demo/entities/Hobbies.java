package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Hobbies implements Serializable {
	@Id
	@GeneratedValue
	int Id_hob;
	
	@ManyToOne
	@JoinColumn(name = "id_cv")
	private CV cv;
	
	private String hobbie;

	public Hobbies() {
		super();
	}

	public Hobbies(CV cv, String hobbie) {
		super();
		this.cv = cv;
		this.hobbie = hobbie;
	}

	public CV getCv() {
		return cv;
	}

	public void setCv(CV cv) {
		this.cv = cv;
	}

	public String getHobbie() {
		return hobbie;
	}

	public void setHobbie(String hobbie) {
		this.hobbie = hobbie;
	}

}
