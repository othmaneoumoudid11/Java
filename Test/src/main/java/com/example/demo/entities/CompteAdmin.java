package com.example.demo.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CA")
public class CompteAdmin extends Compte {

	
	
	public CompteAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompteAdmin( String mail_comp, String modt_comp, boolean active) {
		super( mail_comp, modt_comp, active);
	}
	

}
