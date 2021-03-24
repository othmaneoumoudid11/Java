package com.example.demo.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Offre implements Serializable {
	@Id
	@GeneratedValue
	int id_off;
	@Column(length=512)
	private String dom_off;
	private Date dat_off;
	@Column(length=1000)
	private String desc_off;
	@Column(length=1000)
	private String comp_off;
	private String dur_off;

	private String sujet_off;

	@ManyToMany
	@JoinTable(name = "Postuler", joinColumns = { @JoinColumn(name = "id_off") }, inverseJoinColumns = {
			@JoinColumn(name = "id_etd") })
	private Collection<Etudiant> etudiants =new ArrayList();

	@ManyToOne
	@JoinColumn(name = "id_entr")
	private Entreprise entreprise;

	public Offre() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Offre(Entreprise entreprise, String dom_off, Date dat_off, String desc_off, String comp_off,
			String dur_off, String sujet_off) {

		this.entreprise = entreprise;
		this.dom_off = dom_off;
		this.dat_off = dat_off;
		this.desc_off = desc_off;
		
		this.comp_off = comp_off;
		this.dur_off = dur_off;
		
		this.sujet_off = sujet_off;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public String getDom_off() {
		return dom_off;
	}

	public void setDom_off(String dom_off) {
		this.dom_off = dom_off;
	}

	public String getDat_off() {
		//return (LocalDate) dat_off;
		SimpleDateFormat year = new SimpleDateFormat("yyyy-MM-dd"); 
		return year.format(dat_off);
	}

	public void setDat_off(Date dat_off) {
		this.dat_off = dat_off;
	}

	public String getDesc_off() {
		return desc_off;
	}

	public void setDesc_off(String desc_off) {
		this.desc_off = desc_off;
	}

	

	public String getComp_off() {
		return comp_off;
	}

	public void setComp_off(String comp_off) {
		this.comp_off = comp_off;
	}

	public String getDur_off() {
		return dur_off;
	}

	public void setDur_off(String dur_off) {
		this.dur_off = dur_off;
	}

	

	public String getSujet_off() {
		return sujet_off;
	}

	public void setSujet_off(String sujet_off) {
		this.sujet_off = sujet_off;
	}

	public int getId_off() {
		return id_off;
	}

	public Collection<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(Collection<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	

	
	

}
