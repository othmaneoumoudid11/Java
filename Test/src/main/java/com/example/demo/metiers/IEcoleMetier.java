package com.example.demo.metiers;

import java.util.Collection;

import com.example.demo.entities.CompteEtudiant;
import com.example.demo.entities.Convention;
import com.example.demo.entities.Ecole;
import com.example.demo.entities.Entreprise;
import com.example.demo.entities.Etudiant;
import com.example.demo.entities.Filiere;
import com.example.demo.entities.Offre;

public interface IEcoleMetier {
	public Etudiant TrouveEtudiant(int id_etd);
	public void InscrireEtudiant(int id_ecole,int id_etd);
	public Convention GenereConvention(int id_etd , int id_off, int id_ecole);
	public Collection<Etudiant> ConsulterEtudiantsNStage(int id_ecole);
	public Collection<Etudiant> ListeEtudiant(int id_ecole);
	public void ModifierEtudiant( String first_name, String last_name , String email ,String phone,int id_filiere, String domaine, Integer id_etd);
	public void Ajouter_Etudiant(Ecole ecole, String nom_etd, String prenom_etd, String mail_etd, String tele_etd, String dom_etd,int id_filiere);
	public Collection<Etudiant> EtudiantsSansCompte(int id_ecole);
	public Collection<Entreprise> ListeEntreprise();
	public String GenererPasse(); 
	public Collection<Convention> ConventionsEcole(int id_eco);
	public void AccepterSujet(int id_conv);
	public int countEtudiant(int id_eco);
	public int countEtudiantStagiaire(int id_eco);
	public int countEntreprise();
	public int CountEtudiantSansStage(int id_eco);
	public Collection<Filiere> ListeFiliere(int id_ecole);
	public void Ajouter_Filiere(Ecole ecole , String filiere , String Abreviation);
	public void Modifier_filiere(int id_filiere , String filiere , String Abreviation );
	public void Supprimer_filiere(int id_filiere);
	public void ConventionAttribuer(int id_etd , int id_off);
	public void Valid_cand(int id_etd, int id_off);
	public void Non_Valid_cand(int id_etd,int id_off);
	//////
	public void acc0(int id_cnv);
	public void Supprimer_Etudiant_cv(int id_cv);
} 
