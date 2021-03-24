package com.example.demo.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CR")
public class CompteResponsable extends Compte{

	public CompteResponsable(String mail_comp, String modt_comp, boolean active) {
		super(mail_comp, modt_comp, active);
		// TODO Auto-generated constructor stub
	}

	public CompteResponsable() {
		super();
		// TODO Auto-generated constructor stub
	}

}
