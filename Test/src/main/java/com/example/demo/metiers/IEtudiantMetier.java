package com.example.demo.metiers;

import java.util.Collection;

import org.springframework.data.repository.query.Param;

import com.example.demo.entities.*;

public interface IEtudiantMetier {
	public Collection<Offre> ListerOffresEtudiant(String Domaine);
	public void postuleOffre(int id_offre, int id_etd);
	public void InsererCV(int id_etd, String linkdin, String ghithub, String info);
	public void UpdateCV(int id_etd, String linkdin, String ghithub, String info);
	public void AjouterExperience(int id_cv, String date_debut, String date_fin, String desc_exp, String Experience) ;
	public void AjouteHobie(int id_cv, String Hobie) ;
	public void AjouteLangue(int id_cv, String langue, String niveau) ;
	public void AjouteProjetsRealiser(int id_cv, String date, String desc_proj, String sujet_proj) ;
	public void AjouteFormation(int id_cv, String date_debut, String date_fin, String formation, String source) ;
	public Collection<Formation> ListFormation(int id_etd);
	public Collection<Experiences> ListExperience(int id_etd);
	public Collection<ProjetsReal> ListProjet(int id_etd);
	public Collection<Langues> ListLangues(int id_etd);
	public Collection<Hobbies> ListHobbies(int id_etd);
	public void AfficheLettreMotivation(String cont_let, int id_off ,int id_etd);
	public Collection<Entretien> ListeEntretien(int id_etd);
	public void AccepteEntretirn(int id_ent);
	public void RefuserEntretien(int id_ent);
	public Collection<Entretien> ListeEntretienValider(int id_etd);
	public void DemandeConvention(int id_etd , int id_off);
	public Collection<Convention> ConventionsEtudiant(int id_etd );
	public Convention GetConvention(int id_conv);
	public Collection<Stage> ListeStageEtudiant(int id_etd);
	
	public int nbreStage(int id_etd);
	public int nbreProjet(int id_etd);
	public int nbreDiplome(int id_etd);
	public int nbreLangue(int id_etd);
	
	
	public void StageTrouve(int id_etd);
	public void NonStage(int id_etd);
	public Etudiant select_Etudiant(int id_etd);
	public int [] Nbr_stages_an(int id_etd);
	public int [] nbr_entretiens_an(int id_etd);
	
	//probleme de cv
	public int VerifierCV(int id_etd);
	//probleme de postuler
	public int VerifierPostuler(int id_etd,int id_off);
	
	
}
