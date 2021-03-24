package com.example.demo.metiers;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

import com.example.demo.entities.*;

public interface IEntrepriseMetier {
	
	public Offre CreeOffre(int id_entr , String dom_off, Date dat_off, String desc_off, String comp_off,String dur_off,  String sujet_off);
	
	public Collection<Offre> ConsulterOffres(int id_entr);
	
	public CompteEntrepriseInterne CreeCompteIntern(int id_entr , String email , String MDP);
	
	//public void NoterStage(Stage stage);
	
	public Collection<Etudiant> ConsulterListePostule( int id_offre);
	
	public void CovoqueEntretien(int id_entreprise, int id_offre, int id_etudiant);
	
	public void SupprimerOffre(int id_offre);
	
	public  Entreprise EntrepriseCompte(Integer id_compte);
	
	public void Valider_Condidature(int id_e);

	public Collection<CompteEntrepriseInterne> ListeCompteInterne(Integer id_entreprise);
	
	public void SupprimerCompteInterne(Integer id_compte);
	
	public Collection<Etudiant> ListEtudiantPostuler(Integer id_offre);
	
	public CV CVEtudiant(int id_etd);
	
	public Collection<Experiences> CVEtudiantExperience(int id_etd);
	
	public Collection<Hobbies> CVEtudiantHobbies(int id_etd);
	
	public Collection<ProjetsReal> CVEtudiantProjet(int id_etd);
	
	public Collection<Formation> CVEtudiantFormation(int id_etd);
	
	public Collection<Langues> CVEtudiantLangues(int id_etd);
	
	public LettreMotivation Lettre_motivation(int id_etd,int id_off);
	
	public void ConvocationEntre(int id_etd, int id_off, int id_etrp, LocalDateTime date);
	
	public void RemovePostule(int id_etd,int id_off);
	
	public Collection<Entretien> ListeStagiaire(int id_entreprise);
	
	public Convention ConventionEtudiant(int id_etd,int id_off);
	
	public Collection<Entretien> ListEntretientsEntreprise(int id_rntrep);
	
	public void AjouteStage(int id_etd,int id_off, int id_entrp);
	
	
	public void AjouteNoteStage(int id_etd,double not ,int id_off);
	
	public void ArchiverEtudiant(int id_e , int id_of);
	
	public void Stage_Trouver(int id_etd);
	
	public void Stage_Fini(int id_etd);
	
	public int nbr_offres(int id_ent);
	public int nbr_entretiens(int id_ent);
	public int nbr_stagaires(int id_ent);
	public int nbre_intern_compte(int id_ent);
	
	public int [] nbr_offres_month(int id_ent);
	public int [] nbr_entretiens_month(int id_ent);
	
	public void Valider_Convention(int id_e);
	public void acc0(int id_entr);

}
