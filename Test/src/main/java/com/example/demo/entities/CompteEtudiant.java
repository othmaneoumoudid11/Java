package com.example.demo.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CED")
public class CompteEtudiant extends Compte {
    

	
	public CompteEtudiant(int id_ecole,String mail_comp, String modt_comp, boolean active) {
		super(mail_comp, modt_comp, active);
		// TODO Auto-generated constructor stub
	}

	public CompteEtudiant() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
