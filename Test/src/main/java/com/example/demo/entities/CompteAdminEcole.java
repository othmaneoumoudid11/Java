package com.example.demo.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CAE")
public class CompteAdminEcole extends Compte {

	public CompteAdminEcole() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompteAdminEcole(String mail_comp, String modt_comp, boolean active) {
		super(mail_comp, modt_comp, active);
		// TODO Auto-generated constructor stub
	}

}
