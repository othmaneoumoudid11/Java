package com.example.demo.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CE")
public class CompteEntreprise extends Compte {

	
	public CompteEntreprise() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CompteEntreprise(String mail_comp, String modt_comp, boolean active) {
		super(mail_comp, modt_comp, active);
		
	}

	

}
