package com.example.demo.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CEI")
public class CompteEntrepriseInterne extends Compte {

	private int id_entreprise;
	public CompteEntrepriseInterne() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public CompteEntrepriseInterne(int id_entreprise ,String mail_comp, String modt_comp, boolean active) {
		super(mail_comp, modt_comp, active);
		// TODO Auto-generated constructor stub
		this.id_entreprise=id_entreprise;
	}

	public int getId_entreprise() {
		return id_entreprise;
	}

	public void setId_entreprise(int id_entreprise) {
		this.id_entreprise = id_entreprise;
	}
	
	

}
