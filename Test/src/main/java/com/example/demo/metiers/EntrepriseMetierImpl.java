package com.example.demo.metiers;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.demo.dao.*;
import com.example.demo.entities.CV;
import com.example.demo.entities.Compte;
import com.example.demo.entities.CompteEntrepriseInterne;
import com.example.demo.entities.Convention;
import com.example.demo.entities.Entreprise;
import com.example.demo.entities.Entretien;
import com.example.demo.entities.Etudiant;
import com.example.demo.entities.Experiences;
import com.example.demo.entities.Formation;
import com.example.demo.entities.Hobbies;
import com.example.demo.entities.Langues;
import com.example.demo.entities.LettreMotivation;
import com.example.demo.entities.Offre;
import com.example.demo.entities.ProjetsReal;
import com.example.demo.entities.Stage;

@Service
@Transactional
public class EntrepriseMetierImpl implements IEntrepriseMetier {
	
	 @Autowired
	 OffreRepository offreRepository;
	 @Autowired
	 EntrepriseRepository entrepriseRepository;
	 @Autowired
	 EtudiantRepository etudiantRepository;
	 @Autowired
	 CompteRepository compteRepository;
	 @Autowired
	 StageRepository stageRepository;
	 
	 @Autowired
	 LettreMotivationRepository lettreMotivationRepository;
	 @Autowired
	 ConventionRepository conventionRepository;
	 
	 
	 @Autowired
	 EntretienRepository entretienRepository;
	 
	 @Autowired
	 CVRepository cvRepository;
	 
	@Override
	public Offre CreeOffre(int id_entr, String dom_off, Date dat_off, String desc_off , String comp_off,String dur_off, String sujet_off) {
		Entreprise E =  entrepriseRepository.findById(id_entr).orElse(null);
		Offre o = offreRepository.save(new Offre(E,  dom_off,  dat_off,  desc_off,  comp_off, dur_off,  sujet_off) );
		return o;
	}
	
	@Override
	public Collection<Offre> ConsulterOffres(int id_entr) {
		return offreRepository.ListOffres(id_entr);
	}
	
	@Override
	public CompteEntrepriseInterne CreeCompteIntern(int id_entr, String email, String MDP) {
		return compteRepository.save(new CompteEntrepriseInterne(id_entr,email,MDP,true));
	}
	@Override
	public Collection<Etudiant> ConsulterListePostule(int id_offre) {
        return offreRepository.ListEtudiantPostule(id_offre);
	}
	
	@Override
	public void CovoqueEntretien(int id_entreprise, int id_offre, int id_etudiant) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void SupprimerOffre(int id_offre) {
		Offre O = offreRepository.findById(id_offre).orElse(null);
		O.getEtudiants().clear();
		conventionRepository.Supprimer(id_offre);
		offreRepository.deleteById(id_offre);
	}

	@Override
	public Entreprise EntrepriseCompte(Integer id_compte) {
		return entrepriseRepository.EntrepriseCompte(id_compte);
	}

	@Override
	public Collection<CompteEntrepriseInterne> ListeCompteInterne(Integer id_entreprise) {
		Collection<Compte> Liste = compteRepository.findAll();
	    Collection<CompteEntrepriseInterne> CEI = new ArrayList<>();
//	    for(Compte C : Liste) {
//	    System.out.println("dddd"+C.getMail_comp());
//	    }
		for(Compte C : Liste) {
			
			if(C instanceof CompteEntrepriseInterne) {
				CompteEntrepriseInterne CI = (CompteEntrepriseInterne) C;
				if(CI.getId_entreprise()==id_entreprise) {
					CEI.add(CI);
				}
			}
		}
	    
	    return CEI;
	}

	@Override
	public void SupprimerCompteInterne(Integer id_compte) {
		compteRepository.deleteById(id_compte);
	}

	@Override
	public Collection<Etudiant> ListEtudiantPostuler(Integer id_offre) {
		Offre O = offreRepository.findById(id_offre).orElse(null);
		Collection<Etudiant> E = O.getEtudiants();
		Collection<Etudiant> EE = new ArrayList<>();
		for(Etudiant e : E) {
			  EE.add(e);
		  }
		return EE;
	}

	@Override
	public CV CVEtudiant(int id_etd) {
		return cvRepository.getCVEtudiant(id_etd);	
	}

	@Override
	public Collection<Experiences> CVEtudiantExperience(int id_etd) {
		CV cv = cvRepository.getCVEtudiant(id_etd);
		Collection<Experiences> ex = new ArrayList<>();
		for(Experiences e : cv.getExperiences()){
			ex.add(e);
			}
		return ex;
	}

	@Override
	public Collection<Hobbies> CVEtudiantHobbies(int id_etd) {
		CV cv = cvRepository.getCVEtudiant(id_etd);
		Collection<Hobbies> hb = new ArrayList<>();
		for(Hobbies h : cv.getHobbies()){
			hb.add(h);
			}
		return hb;
	}

	@Override
	public Collection<ProjetsReal> CVEtudiantProjet(int id_etd) {
		CV cv = cvRepository.getCVEtudiant(id_etd);
		Collection<ProjetsReal> pr = new ArrayList<>();
		for(ProjetsReal p : cv.getProjetsReals()){
			pr.add(p);
			}
		return pr;
	}

	@Override
	public Collection<Formation> CVEtudiantFormation(int id_etd) {
		CV cv = cvRepository.getCVEtudiant(id_etd);
		Collection<Formation> fr = new ArrayList<>();
		for(Formation f : cv.getFormations()){
			fr.add(f);
			}
		return fr;
	}

	@Override
	public Collection<Langues> CVEtudiantLangues(int id_etd) {
		CV cv = cvRepository.getCVEtudiant(id_etd);
		Collection<Langues> l = new ArrayList<>();
		for(Langues lg : cv.getLangues()){
			l.add(lg);
			}
		return l;
	}

	@Override
	public LettreMotivation Lettre_motivation(int id_etd, int id_off) {
          return lettreMotivationRepository.getLettre(id_etd,id_off);
	}

	@Override
	public void ConvocationEntre(int id_etd, int id_off, int id_etrp,LocalDateTime date) {
		Etudiant E = etudiantRepository.findById(id_etd).orElse(null);
		Offre O = offreRepository.findById(id_off).orElse(null);
		Entreprise ET = entrepriseRepository.findById(id_etrp).orElse(null);
		entretienRepository.save(new Entretien(O,E,ET,date));
	}

	@Override
	public void RemovePostule(int id_etd, int id_off) {
	
			Offre O = offreRepository.findById(id_off).orElse(null);
			Etudiant E = etudiantRepository.findById(id_etd).orElse(null);
			
		    //E.getOffres().add(O);
		    O.getEtudiants().remove(E);
	}

	@Override
	public Collection<Entretien> ListeStagiaire(int id_entreprise) {
		return entretienRepository.ListeStagiaire(id_entreprise);
	}

	@Override
	public Convention ConventionEtudiant(int id_etd, int id_off) {
		return conventionRepository.ConventionEntreprise(id_etd,id_off);
	}

	@Override
	public Collection<Entretien> ListEntretientsEntreprise(int id_entrep) {
		return entretienRepository.ListeEntretienValiderEtudiant(id_entrep);
	}

	@Override
	public void Valider_Condidature(int id_e) {
		
		entretienRepository.validerCondidature(id_e);
		
	}
	
	@Override
	public void Valider_Convention(int id_e) {
		
		entretienRepository.ValiderConvention(id_e);
		
	}
	

	@Override
	public void AjouteStage(int id_etd, int id_off,int id_entrp) {
		Offre O = offreRepository.findById(id_off).orElse(null);
		Etudiant E = etudiantRepository.findById(id_etd).orElse(null);
		Entreprise EP = entrepriseRepository.findById(id_entrp).orElse(null);
		stageRepository.save(new Stage(O, E, EP, 0));
	}

	@Override
	public void ArchiverEtudiant(int id_e, int id_of) {
		entretienRepository.archiverEtudiant(id_e,id_of);
	}

	@Override
	public void AjouteNoteStage(int id_etd, double not, int id_off) {
		stageRepository.NoteStage(not,id_etd, id_off);
	}

	@Override
	public void Stage_Trouver(int id_etd) {
		etudiantRepository.Stage_Trouver(id_etd);
	}

	@Override
	public void Stage_Fini(int id_etd) {
		etudiantRepository.Stage_Fini(id_etd);
	}

	@Override
	public int nbr_offres(int id_ent) { 

		return offreRepository.nbr_offres(id_ent);
	}

	@Override
	public int nbr_entretiens(int id_ent) {
		
		return entretienRepository.nbr_entretiens(id_ent);
	}

	@Override
	public int nbr_stagaires(int id_ent) {
		// TODO Auto-generated method stub
		return stageRepository.nbr_stagaires(id_ent);
	}

	@Override
	public int nbre_intern_compte(int id_ent) {
		// TODO Auto-generated method stub
		Collection<Compte> cpts = compteRepository.nbre_intern_compte();
		int i = 0;
		for(Compte c : cpts) {
			if( (c instanceof CompteEntrepriseInterne) && ((CompteEntrepriseInterne) c).getId_entreprise() == id_ent) {
				i++;
			}
		}
		 return i;
	}

	@Override
	public int[] nbr_offres_month(int id_ent) {
		int yr = new Date().getYear();
		yr = yr - 100;
	    int [] yrs = new int [12];
	    yrs[0] = offreRepository.nbr_offres_month(id_ent,"JAN",yr);
	    yrs[1] = offreRepository.nbr_offres_month(id_ent , "FEB" , yr);
	    yrs[2] = offreRepository.nbr_offres_month(id_ent, "MAR", yr); 
	    yrs[3] = offreRepository.nbr_offres_month(id_ent,"AVR",yr);
	    yrs[4] = offreRepository.nbr_offres_month(id_ent , "MAI" , yr);
	    yrs[5] = offreRepository.nbr_offres_month(id_ent, "JUI", yr);   
	    yrs[6] = offreRepository.nbr_offres_month(id_ent,"JUIL",yr);
	    yrs[7] = offreRepository.nbr_offres_month(id_ent , "AOU" , yr);
	    yrs[8] = offreRepository.nbr_offres_month(id_ent, "SEP", yr); 
	    yrs[9] = offreRepository.nbr_offres_month(id_ent,"OCT",yr);
	    yrs[10] = offreRepository.nbr_offres_month(id_ent , "NOV" , yr);
	    yrs[11] = offreRepository.nbr_offres_month(id_ent, "DEC", yr); 
		return yrs;
	}

	@Override
	public int[] nbr_entretiens_month(int id_ent) {
		// TODO Auto-generated method stub
		//return entretienRepository.nbr_entretiens_month( id_ent, month, year);
		int yr = new Date().getYear();
		yr = yr - 100;
	    int [] yrs = new int [12];
	    yrs[0] = entretienRepository.nbr_entretiens_month(id_ent,"JAN",yr);
	    yrs[1] = entretienRepository.nbr_entretiens_month(id_ent , "FEB" , yr);
	    yrs[2] = entretienRepository.nbr_entretiens_month(id_ent, "MAR", yr); 
	    yrs[3] = entretienRepository.nbr_entretiens_month(id_ent,"AVR",yr);
	    yrs[4] = entretienRepository.nbr_entretiens_month(id_ent , "MAI" , yr);
	    yrs[5] = entretienRepository.nbr_entretiens_month(id_ent, "JUI", yr);   
	    yrs[6] = entretienRepository.nbr_entretiens_month(id_ent,"JUIL",yr);
	    yrs[7] = entretienRepository.nbr_entretiens_month(id_ent , "AOU" , yr);
	    yrs[8] = entretienRepository.nbr_entretiens_month(id_ent, "SEP", yr); 
	    yrs[9] = entretienRepository.nbr_entretiens_month(id_ent,"OCT",yr);
	    yrs[10] = entretienRepository.nbr_entretiens_month(id_ent , "NOV" , yr);
	    yrs[11] = entretienRepository.nbr_entretiens_month(id_ent, "DEC", yr); 
		return yrs;
	}

	@Override
	public void acc0(int id_entr) {

		entretienRepository.acc0(id_entr);
	}

	
	

	  
}
	


