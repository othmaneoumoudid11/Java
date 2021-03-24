package com.example.demo.metiers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.CVRepository;
import com.example.demo.dao.CompetencesRepository;
import com.example.demo.dao.CompteRepository;
import com.example.demo.dao.ConventionRepository;
import com.example.demo.dao.EcoleRepository;
import com.example.demo.dao.EntrepriseRepository;
import com.example.demo.dao.EntretienRepository;
import com.example.demo.dao.EtudiantRepository;
import com.example.demo.dao.ExperiencesRepository;
import com.example.demo.dao.FiliereRepository;
import com.example.demo.dao.FormationsRepository;
import com.example.demo.dao.HobbiesRepository;
import com.example.demo.dao.LanguesRepository;
import com.example.demo.dao.LettreMotivationRepository;
import com.example.demo.dao.OffreRepository;
import com.example.demo.dao.ProjetsRealRepository;
import com.example.demo.dao.StageRepository;
import com.example.demo.entities.CompteEtudiant;
import com.example.demo.entities.Convention;
import com.example.demo.entities.Ecole;
import com.example.demo.entities.Entreprise;
import com.example.demo.entities.Entretien;
import com.example.demo.entities.Etudiant;
import com.example.demo.entities.Filiere;
import com.example.demo.entities.Offre;
import com.example.demo.entities.Stage;

@Service
@Transactional
public class EcoleMetierImpl implements IEcoleMetier {

	@Autowired
	EtudiantRepository etudiantRepository;
	@Autowired
	CompteRepository compteRepository;
	@Autowired
	ConventionRepository conventionRepository;
	@Autowired
	OffreRepository offreRepository;
	@Autowired
	CVRepository cvRepository;
	@Autowired
	LanguesRepository languesRepository;

	
	
	@Autowired
	EcoleRepository ecoleRepository;

	@Autowired
	EntrepriseRepository entrepriseRepository;

	@Autowired
	StageRepository stageRepository;
	
	@Autowired
	FiliereRepository filiereRepository;
	
	@Autowired
	EntretienRepository entretienRepository;
	@Autowired
	HobbiesRepository hobbiesRepository;
	@Autowired
	FormationsRepository formationsRepository;
	@Autowired
	ExperiencesRepository experiencesRepository;
	@Autowired
	CompetencesRepository competencesRepository;
	@Autowired
	ProjetsRealRepository projetsRealRepository1;
	@Autowired
    LettreMotivationRepository lettreMotivationRepository;	
	@Override
	public Etudiant TrouveEtudiant(int id_etd) {
		Etudiant etd = etudiantRepository.findById(id_etd).orElse(null);
		if (etd == null)
			throw new RuntimeException("Compte introuvable");
		return etd;
	}

	@Override
	public void InscrireEtudiant(int id_ecole,int id_etd) {
		Etudiant etd = TrouveEtudiant(id_etd);
		CompteEtudiant CE= compteRepository.save(new CompteEtudiant(id_ecole,etd.getMail_etd(), GenererPasse(), true));
		etd.setCompte(CE);
	}

	@Override
	public Convention GenereConvention(int id_etd, int id_off, int id_ecole) {
		Etudiant etudiant = TrouveEtudiant(id_etd);
		Offre offre = offreRepository.findById(id_off).orElse(null);
		Ecole ecole = ecoleRepository.findById(id_ecole).orElse(null);
		Convention C = conventionRepository.save(new Convention(etudiant, offre, ecole));
		return C;
	}

	@Override
	public Collection<Etudiant> ConsulterEtudiantsNStage(int id_ecole) {
		return etudiantRepository.ListEtudiantSansStage(id_ecole);
	}

	@Override
	public Collection<Etudiant> ListeEtudiant(int id_ecole) {
		return etudiantRepository.findAll();
	}

	@Override
	public void ModifierEtudiant( String first_name, String last_name, String email,String phone, int id_filiere , String domaine , Integer id_etd) {
		Filiere filiere = filiereRepository.findById(id_filiere).orElse(null);
		etudiantRepository.ModifierEtudiant(first_name, last_name, email, phone, filiere, domaine, id_etd);
	}

	@Override
	public Collection<Etudiant> EtudiantsSansCompte(int id_ecole) {
		return etudiantRepository.EtudiantSansCompte(id_ecole);
	}
	
	public Collection<Entreprise> ListeEntreprise(){
		return entrepriseRepository.findAll();
	}

	@Override
	public String GenererPasse() {
		 String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		    String randomString =""; 
		    Random rand = new Random();
		    char[] text = new char[6];  
		    
		    for(int i=0;i<6;i++) {
		    	text[i] = characters.charAt(rand.nextInt(characters.length()));
		    }
		    
		    for(int i=0;i<6;i++) { 
		    	randomString += text[i];  
		    }
		    return randomString;
	}

	@Override
	public Collection<Convention> ConventionsEcole(int id_eco) {
		return conventionRepository.ConventionsEcole(id_eco);
	}

	@Override
	public void AccepterSujet(int id_conv) {
		conventionRepository.AccepterSujet(id_conv);
	}

	@Override
	public int countEtudiant(int id_eco) {
		return etudiantRepository.countetudiant(id_eco);
	}

	@Override
	public int countEtudiantStagiaire(int id_eco) {
		return etudiantRepository.countEtudiantStagiaire(id_eco);
	}

	@Override
	public int countEntreprise() {
		return entrepriseRepository.countEntreprise();
	}
	
	@Override
	public void Supprimer_Etudiant_cv(int id_cv) {
	    languesRepository.Supprimer_lang_etudiant(id_cv);
		hobbiesRepository.Supprimer_hobi_etudiant(id_cv);
	    formationsRepository.Supprimer_Form_etudiant(id_cv);
	    experiencesRepository.Supprimer_exp_etudiant(id_cv);
	    competencesRepository.Supprimer_comp_etudiant(id_cv);
	    projetsRealRepository1.Supprimer_projr_etudiant(id_cv);
		cvRepository.Supprimer_cv_etudiant(id_cv);
	}

	@Override
	public int CountEtudiantSansStage(int id_eco) {
        return etudiantRepository.CountEtudiantSansStage(id_eco);
	}

	@Override
	public Collection<Filiere> ListeFiliere(int id_ecole) {
		return filiereRepository.ListeFiliere(id_ecole);
	}

	@Override
	public void Ajouter_Filiere(Ecole ecole, String filiere, String Abreviation) {
		filiereRepository.save(new Filiere(ecole, filiere, Abreviation));
	}

	@Override
	public void Modifier_filiere(int id_filiere, String filiere, String Abreviation) {
		filiereRepository.Modifier_filiere(filiere,Abreviation,id_filiere);
	}

	@Override
	public void Supprimer_filiere(int id_filiere) {
		filiereRepository.deleteById(id_filiere);
	}

	@Override
	public void Ajouter_Etudiant(Ecole ecole, String nom_etd, String prenom_etd, String mail_etd, String tele_etd, String dom_etd, int id_filiere) {
		Filiere filiere = filiereRepository.findById(id_filiere).orElse(null);
		Etudiant ETD = new Etudiant(  ecole,  nom_etd,  prenom_etd,  mail_etd,  tele_etd,  dom_etd, filiere, false);
		etudiantRepository.save(ETD);
	}

	@Override
	public void ConventionAttribuer(int id_etd, int id_off) {
		entretienRepository.ConventionAttribuer(id_etd,id_off);
	}

	@Override
	public void Valid_cand(int id_etd, int id_off) {

		entretienRepository.valid_cand(id_etd,id_off);
	}

	@Override
	public void Non_Valid_cand(int id_etd, int id_off) {

		entretienRepository.Non_valid_cand(id_etd,id_off);

	}

	@Override
	public void acc0(int id_cnv) {

		conventionRepository.acc0(id_cnv);
	}


	
	
}
