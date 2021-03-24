package com.example.demo.metiers;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.CVRepository;
import com.example.demo.dao.CompteRepository;
import com.example.demo.dao.ConventionRepository;
import com.example.demo.dao.EcoleRepository;
import com.example.demo.dao.EntrepriseRepository;
import com.example.demo.dao.EntretienRepository;
import com.example.demo.dao.EtudiantRepository;
import com.example.demo.dao.ExperiencesRepository;
import com.example.demo.dao.FormationsRepository;
import com.example.demo.dao.HobbiesRepository;
import com.example.demo.dao.LanguesRepository;
import com.example.demo.dao.LettreMotivationRepository;
import com.example.demo.dao.OffreRepository;
import com.example.demo.dao.ProjetsRealRepository;
import com.example.demo.dao.StageRepository;
import com.example.demo.entities.*;


@Service
@Transactional
public class EtudiantMetierImpl implements IEtudiantMetier{
	
	@Autowired
	EtudiantRepository etudiantRepository;
	@Autowired
	CompteRepository compteRepository;
	@Autowired
	ConventionRepository conventionRepository;
	@Autowired
	OffreRepository offreRepository;

	@Autowired
	EcoleRepository ecoleRepository;

	@Autowired
	EntrepriseRepository entrepriseRepository;

	@Autowired
	CVRepository cvRepository;
	
	@Autowired
	ExperiencesRepository experiencesRepository;
	
	@Autowired
	HobbiesRepository hobbiesRepository;
	
	@Autowired
	LanguesRepository languesRepository;
	
	@Autowired
	ProjetsRealRepository projetsRealRepository;
	
	@Autowired
	FormationsRepository formationsRepository;
	
	@Autowired
	LettreMotivationRepository lettreMotivationRepository;
	
	@Autowired
	EntretienRepository entretienRepository;
	
	
	@Autowired
	StageRepository stageRepository;
	

	
	
	
	@Override
	public Collection<Offre> ListerOffresEtudiant(String Domaine) {
		return offreRepository.ListOffresEtudiat( Domaine);
	}

	@Override
	public void postuleOffre(int id_offre, int id_etd) {
		
		Offre O = offreRepository.findById(id_offre).orElse(null);
		Etudiant E = etudiantRepository.findById(id_etd).orElse(null);
		
	    //E.getOffres().add(O);
	    O.getEtudiants().add(E);
		
	}

	@Override
	public void InsererCV(int id_etd,String linkdin, String ghithub, String info) {
		Etudiant E = etudiantRepository.findById(id_etd).orElse(null);
		CV cv = new CV(E, linkdin, ghithub, info);
		cvRepository.save(cv);
	}

	@Override
	public void AjouterExperience(int id_cv, String date_debut, String date_fin, String desc_exp, String Experience) {
		    CV cv = cvRepository.findById(id_cv).orElse(null);
		   

	       
	        LocalDate DateF = LocalDate.parse(date_fin);
	        LocalDate DateD = LocalDate.parse(date_debut);
		    experiencesRepository.save(new Experiences(cv, Experience, DateD, DateF, desc_exp));
		
	}
	@Override
	public void AjouteHobie(int id_cv, String hobie) {
		   
		 CV cv = cvRepository.findById(id_cv).orElse(null);
	
		 hobbiesRepository.save(new Hobbies(cv, hobie));
	}
	@Override
	public void AjouteLangue(int id_cv, String langue, String niveau) {
	    CV cv = cvRepository.findById(id_cv).orElse(null);
	    languesRepository.save(new Langues(cv, langue, niveau));
	}
	@Override
	public void AjouteProjetsRealiser(int id_cv, String date, String desc_proj, String sujet_proj) {
	    CV cv = cvRepository.findById(id_cv).orElse(null);
	    LocalDate DateF = LocalDate.parse(date);
	    projetsRealRepository.save(new ProjetsReal(cv,  sujet_proj, desc_proj,DateF));
	}
	
	@Override
	public void AjouteFormation(int id_cv, String date_debut, String date_fin, String formation, String source) {
		LocalDate DateF = LocalDate.parse(date_fin);
        LocalDate DateD = LocalDate.parse(date_debut);
	    CV cv = cvRepository.findById(id_cv).orElse(null);
	    Formation F = new Formation(cv, formation,source,DateD, DateF);
	    cv.getFormations().add(F);
	    formationsRepository.save(F);
	}

	@Override
	public Collection<Formation> ListFormation(int id_etd) {
		Etudiant E = etudiantRepository.findById(id_etd).orElse(null);
		return formationsRepository.ListeFormations(cvRepository.getCVEtudiant(id_etd).getId_cv());
		
	}

	@Override
	public Collection<Experiences> ListExperience(int id_etd) {
		Etudiant E = etudiantRepository.findById(id_etd).orElse(null);
		return experiencesRepository.ListeExperiences(cvRepository.getCVEtudiant(id_etd).getId_cv());
	}
	
	@Override
	public Collection<ProjetsReal> ListProjet(int id_etd) {
		Etudiant E = etudiantRepository.findById(id_etd).orElse(null);
		return projetsRealRepository.ListeProjet(cvRepository.getCVEtudiant(id_etd).getId_cv());
	}

	@Override
	public Collection<Langues> ListLangues(int id_etd) {
		Etudiant E = etudiantRepository.findById(id_etd).orElse(null);
		return languesRepository.ListeLangues(cvRepository.getCVEtudiant(id_etd).getId_cv());
	}

	@Override
	public Collection<Hobbies> ListHobbies(int id_etd) {
		Etudiant E = etudiantRepository.findById(id_etd).orElse(null);
		return hobbiesRepository.ListeHobbies(cvRepository.getCVEtudiant(id_etd).getId_cv());
	}

	@Override
	public void AfficheLettreMotivation(String cont_let, int id_off, int id_etd) {
		Etudiant E = etudiantRepository.findById(id_etd).orElse(null);
		Offre O = offreRepository.findById(id_off).orElse(null);
		lettreMotivationRepository.save(new LettreMotivation(cont_let, O, E, ""));
	}

	@Override
	public Collection<Entretien> ListeEntretien(int id_etd) {
		return entretienRepository.ListeEntretien(id_etd) ;
	}

	@Override
	public void AccepteEntretirn(int id_ent) {
		entretienRepository.AcceptEntretien(id_ent);
	}

	@Override
	public void RefuserEntretien(int id_ent) {
		entretienRepository.RefuserEntretien(id_ent);
	}

	@Override
	public Collection<Entretien> ListeEntretienValider(int id_etd) {
		return entretienRepository.ListeEntretienValider(id_etd);
		
	}

	@Override
	public void DemandeConvention( int id_etd, int id_off) {
		Etudiant E = etudiantRepository.findById(id_etd).orElse(null);
		Offre O = offreRepository.findById(id_off).orElse(null);
		
		// ajouter cette fonction qui verifier est ce quelle deja existe
		
		if((conventionRepository.verifierConvention(E.getId_etd(),O.getId_off()) )==0) {
			conventionRepository.save(new Convention(E,O,E.getEcole()));
		}else {
			System.out.println();
		}
		
	}

	@Override
	public Collection<Convention> ConventionsEtudiant(int id_etd) {
		return conventionRepository.ConventionsEtudiant(id_etd);
	}

	@Override
	public Convention GetConvention(int id_conv) {

		return conventionRepository.findById(id_conv).orElse(null);
	}

	@Override
	public Collection<Stage> ListeStageEtudiant(int id_etd) {
		return stageRepository.ListeStage(id_etd);
	}

	@Override
	public int nbreStage(int id_etd) {
		return stageRepository.nbreStage(id_etd);
	}

	@Override
	public int nbreProjet(int id_etd) {
		return projetsRealRepository.nbreProjet(id_etd);
	}

	@Override
	public int nbreDiplome(int id_etd) {
		return formationsRepository.nbreDiplome(id_etd);
	}

	@Override
	public int nbreLangue(int id_etd) {
		return languesRepository.nbreLangue(id_etd);
	}

	@Override
	public void StageTrouve(int id_etd) {
		etudiantRepository.StageTrouve(id_etd);
	}

	@Override
	public void NonStage(int id_etd) {
       etudiantRepository.NonStage(id_etd);		
	}

	@Override
	public int [] Nbr_stages_an(int id_etd) {
		// TODO Auto-generated method stub
		int AN [] = new int [7];
		
		 AN[0] = entretienRepository.nbr_stages_an(id_etd,16);
		 AN[1] = entretienRepository.nbr_stages_an(id_etd,17);
		 AN[2] = entretienRepository.nbr_stages_an(id_etd,18);
		 AN[3] = entretienRepository.nbr_stages_an(id_etd,19);
		 AN[4] = entretienRepository.nbr_stages_an(id_etd,20);
		 AN[5] = entretienRepository.nbr_stages_an(id_etd,21);
		 AN[6] = entretienRepository.nbr_stages_an(id_etd,22);
		 return AN;

	}

	@Override
	public int [] nbr_entretiens_an(int id_etd) {
		
		int AN [] = new int [7];
		
		 AN[0] = entretienRepository.nbr_entretiens_an(id_etd,16);
		 AN[1] = entretienRepository.nbr_entretiens_an(id_etd,17);
		 AN[2] = entretienRepository.nbr_entretiens_an(id_etd,18);
		 AN[3] = entretienRepository.nbr_entretiens_an(id_etd,19);
		 AN[4] = entretienRepository.nbr_entretiens_an(id_etd,20);
		 AN[5] = entretienRepository.nbr_entretiens_an(id_etd,21);
		 AN[6] = entretienRepository.nbr_entretiens_an(id_etd,22);
		 return AN;
	}

	@Override
	public int VerifierCV(int id_etd) {
		if(cvRepository.getCVEtudiant(id_etd)==null) {
			return 0;
		}else
			return 1;
	}

	@Override
	public void UpdateCV(int id_etd, String linkdin, String ghithub, String info) {
		cvRepository.UpdateCV( linkdin,  ghithub,  info,id_etd);
	}

	@Override
	public int VerifierPostuler(int id_etd, int id_off) {
		return lettreMotivationRepository.verifierLettre(id_etd,  id_off);
	}

	@Override
	public Etudiant select_Etudiant(int id_etd) {

		return etudiantRepository.select_Etudiant(id_etd);
	}

	
	
	
}
