package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_comp", discriminatorType = DiscriminatorType.STRING, length = 4)
public abstract class Compte implements Serializable {
	@Id
	@GeneratedValue
	int id_comp;

	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String mail_comp;
	private String modt_comp;
	private boolean active;
	
	


	public Compte(String mail_comp, String modt_comp, boolean active) {
		super();

		this.mail_comp = mail_comp;
		this.modt_comp = modt_comp;
		this.active = active;
	}

	public int getId_comp() {
		return id_comp;
	}

	public String getMail_comp() {
		return mail_comp;
	}

	public void setMail_comp(String mail_comp) {
		this.mail_comp = mail_comp;
	}

	public String getModt_comp() {
		return modt_comp;
	}

	public void setModt_comp(String modt_comp) {
		this.modt_comp = modt_comp;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}