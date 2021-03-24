package com.example.demo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.dao.CVRepository;
import com.example.demo.dao.CompteRepository;
import com.example.demo.dao.ConventionRepository;
import com.example.demo.dao.EcoleRepository;
import com.example.demo.dao.EntrepriseRepository;
import com.example.demo.dao.EntretienRepository;
import com.example.demo.dao.EtudiantRepository;
import com.example.demo.dao.FiliereRepository;
import com.example.demo.dao.LettreMotivationRepository;
import com.example.demo.dao.StageRepository;
import com.example.demo.entities.Compte;
import com.example.demo.entities.CompteAdminEcole;
import com.example.demo.entities.CompteEntreprise;
import com.example.demo.entities.Ecole;
import com.example.demo.entities.Entreprise;
import com.example.demo.entities.Etudiant;
import com.example.demo.entities.Responsable;
import com.example.demo.entities.Stage;
import com.example.demo.metiers.EcoleMetierImpl;
import com.example.demo.metiers.IEcoleMetier;
import com.example.demo.metiers.IEntrepriseMetier;
import com.example.demo.metiers.ResponsableMetierImpl;

@Controller
@SessionAttributes({ "Responsable" })
public class ControllerResponsable {
	
	
	@Autowired
	ResponsableMetierImpl responsableMetierImpl;
	
	@Autowired
	EcoleRepository ecoleRepository;
	
	
	@Autowired
	EntrepriseRepository entrepriseRepository;
	
	
	@Autowired
	IEcoleMetier ecolemetier;
	
	
	@Autowired
	CompteRepository compteRepository;
	
	@Autowired
	 IEntrepriseMetier entrepriseMetierImpl;
	
	@Autowired
	FiliereRepository filiereRepository;
	
	@Autowired
    StageRepository stageRepository ;
	
	@Autowired
    ConventionRepository conventionRepository ;
	
	@Autowired
    EntretienRepository entretienRepository; 
	
	@Autowired
    LettreMotivationRepository lettreMotivationRepository;
	
	@Autowired
    CVRepository cvRepository ;
	
	@Autowired
    EtudiantRepository etudiantRepository ;

	
	
	@GetMapping(value = "/Acceuil_Responsable")
	public String Acceuil(HttpSession session,Model model) {
		Responsable R = (Responsable) session.getAttribute("Responsable");
		int nbreEcole = responsableMetierImpl.NbreEcole(R.getId_res());
		int nbreEntreprise = responsableMetierImpl.NbreEntreprise(R.getId_res());
		model.addAttribute("Responsable",R);
		model.addAttribute("nbreEcole",nbreEcole);
		model.addAttribute("nbreEntreprise",nbreEntreprise);
		return "Responsable/Acceuil_Responsable";
	}
	

	@GetMapping(value = "/Liste_Ecole")
	public String ListeEcole(HttpSession session,Model model) {
		Responsable R = (Responsable) session.getAttribute("Responsable");
		Collection<Ecole> ecoles = responsableMetierImpl.ListeEcole(R.getId_res());
		model.addAttribute("ecoles", ecoles);
		model.addAttribute("Responsable", R);
		return "Responsable/Liste_Ecole_Responsable";
	}
	
	@PostMapping(value = "/Ajouter_Ecole")
	public String Ajouter_Etudiant(HttpSession session,String nom,String adresse,String tele,String mail,String MDP) {
		Responsable R = (Responsable) session.getAttribute("Responsable");
		CompteAdminEcole C= (CompteAdminEcole) responsableMetierImpl.CompteEcole(mail,MDP);
		responsableMetierImpl.AjouterEcole(C, nom, "", mail ,tele  , adresse,R);
		return "redirect:Liste_Ecole";
	}
	
	
	@PostMapping(value = "/Statistique_Ecole")
	public String Statistique_Ecole(HttpSession session,String id_ecole,Model model) {
		Responsable R = (Responsable) session.getAttribute("Responsable");
		int id_ec = Integer.parseInt(id_ecole);
		Ecole E = ecoleRepository.findById(id_ec).orElse(null);
		int CountEtd = ecolemetier.countEtudiant(E.getId_eco());
		int CountEtdStage = ecolemetier.countEtudiantStagiaire(E.getId_eco());
		model.addAttribute("CountEtdStage",CountEtdStage);
		int CountEntreprise = ecolemetier.countEntreprise();
		model.addAttribute("CountEntreprise",CountEntreprise);
		int CountEtudiantSansStage= ecolemetier.CountEtudiantSansStage(E.getId_eco());
		model.addAttribute("CountEtudiantSansStage",CountEtudiantSansStage);
		model.addAttribute("CountEtd",CountEtd);
		model.addAttribute("Responsable", R);
		model.addAttribute("ecole", E);
		return "Responsable/Statistique_Ecole";
	}
	
	
	
	
	
	@PostMapping(value = "/Statistique_Entreprise")
	public String Statistique_Entreprise(HttpSession session,String id_entreprise,Model model) {
		Responsable R = (Responsable) session.getAttribute("Responsable");
		int id_ent = Integer.parseInt(id_entreprise);
		Entreprise entreprise = entrepriseRepository.findById(id_ent).orElse(null);
		
		int nbr_offres = entrepriseMetierImpl.nbr_offres(entreprise.getId_entr());
		int nbr_entretien = entrepriseMetierImpl.nbr_entretiens(entreprise.getId_entr());
		int nbr_intern_compte = entrepriseMetierImpl.nbre_intern_compte(entreprise.getId_entr());
		int nbr_stagaires = entrepriseMetierImpl.nbr_stagaires(entreprise.getId_entr());
		int [] nbrs_offres_month =  entrepriseMetierImpl.nbr_offres_month(entreprise.getId_entr());
		int [] nbr_entretiens_month = entrepriseMetierImpl.nbr_entretiens_month(entreprise.getId_entr());
		
	    model.addAttribute("nbr_offres", nbr_offres);
	    model.addAttribute("nbr_entretien", nbr_entretien);
	    model.addAttribute("nbr_intern_compte", nbr_intern_compte);
	    model.addAttribute("nbr_stagaires", nbr_stagaires);
	    model.addAttribute("jan_off", nbrs_offres_month[0]);
	    System.out.println(nbrs_offres_month[0]);
	    model.addAttribute("jan_ent", nbr_entretiens_month[0]); 
	    model.addAttribute("feb_off", nbrs_offres_month[1]);
	    model.addAttribute("feb_ent", nbr_entretiens_month[1]);
	    model.addAttribute("mar_off", nbrs_offres_month[2]);
	    model.addAttribute("mar_ent", nbr_entretiens_month[2]);
	    model.addAttribute("avr_off", nbrs_offres_month[3]);
	    model.addAttribute("avr_ent", nbr_entretiens_month[3]);
	    model.addAttribute("mai_off", nbrs_offres_month[4]);
	    model.addAttribute("mai_ent", nbr_entretiens_month[4]);
	    model.addAttribute("jui_off", nbrs_offres_month[5]);
	    model.addAttribute("jui_ent", nbr_entretiens_month[5]);
	    model.addAttribute("juil_off", nbrs_offres_month[6]);
	    model.addAttribute("juil_ent", nbr_entretiens_month[6]);
	    model.addAttribute("aou_off", nbrs_offres_month[7]);
	    model.addAttribute("aou_ent", nbr_entretiens_month[7]);
	    model.addAttribute("sep_off", nbrs_offres_month[8]);
	    model.addAttribute("sep_ent", nbr_entretiens_month[8]);
	    model.addAttribute("oct_off", nbrs_offres_month[9]);
	    model.addAttribute("oct_ent", nbr_entretiens_month[9]);
	    model.addAttribute("nov_off", nbrs_offres_month[10]);
	    model.addAttribute("nov_ent", nbr_entretiens_month[10]);
	    model.addAttribute("dec_off", nbrs_offres_month[11]);
	    model.addAttribute("dec_ent", nbr_entretiens_month[11]);
		
		model.addAttribute("Responsable", R);
		model.addAttribute("entreprise", entreprise);
		return "Responsable/Statistique_Entreprise";
	}
	
	@PostMapping(value = "/Activer_Compte_Ecole")
	public String Activer_Compte_Ecole(HttpSession session,String edit_id) {
		Responsable R = (Responsable) session.getAttribute("Responsable");
		int id_ecole = Integer.parseInt(edit_id);
		responsableMetierImpl.ActiverCompteEcole(id_ecole);
		return "redirect:Liste_Ecole";
	}
	
	@PostMapping(value = "/Desactiver_Compte_Ecole")
	public String Desactiver_Compte_Ecole(HttpSession session,String edit_id) {
		int id_ecole = Integer.parseInt(edit_id);
		
		responsableMetierImpl.DesactiverCompteEcole(id_ecole);
		return "redirect:Liste_Ecole";
	}
	
	@PostMapping(value = "/Supprimer_Compte_Ecole")
	public String Supprimer_Compte_Ecole(HttpSession session,String delete_id) {
	   int ID_CE = Integer.parseInt(delete_id);
	   Ecole E = ecoleRepository.findById(ID_CE).orElse(null);
	   for(Etudiant e : E.getEtudiants()) {
		   int id_etd = e.getId_etd();
		   System.out.println(id_etd);
		   for (Stage S: e.getStages()) {
			   System.out.println(S.getId_stage());
			   stageRepository.Supprimer_stages_etudiant(S.getId_stage());
		   }
		   if(conventionRepository.nbreConvention(id_etd)>0) { 
			   	   conventionRepository.Supprimer_conventions_etudiant(id_etd);
		   }
		   
		   if(entretienRepository.nbr_entretiens_Etudiant(id_etd)>0) {
			    entretienRepository.Supprimer_entretiens_etudiant(id_etd);
		   }
		  
		   if(lettreMotivationRepository.nbreLettreMotivation(id_etd)>0) {
			    lettreMotivationRepository.Supprimer_lettre_etudiant(id_etd);
		   }
		  
		   
		   if((cvRepository.getCVEtudiant(e.getId_etd()))!=null) {
			   ecolemetier.Supprimer_Etudiant_cv(cvRepository.getCVEtudiant(e.getId_etd()).getId_cv());
		   }
		  System.out.println(id_etd);
		   etudiantRepository.SupprimerEtudiant(id_etd);
		  System.out.println("iciiiiiiii2");
		   if(e.getCompte()!=null) {
			   compteRepository.deleteById(e.getCompte().getId_comp());
		   }
	   }
	   filiereRepository.SupprimerFilieres(ID_CE);
	   
	   responsableMetierImpl.SupprimerCompteEcole(E);
	   return "redirect:Liste_Ecole";
	}
	
	
	/*
	  ----------------------------------------------------------------------------------------
	 */
	
	
	@PostMapping(value = "/Activer_Compte_Entreprise")
	public String Activer_Compte_Entreprise(HttpSession session,String edit_id) {
		Responsable R = (Responsable) session.getAttribute("Responsable");
		int ID_ETR = Integer.parseInt(edit_id);
		responsableMetierImpl.ActiverCompteEntreprise(ID_ETR);
		return "redirect:Liste_Entreprise";
	}
	
	@PostMapping(value = "/Desactiver_Compte_Entreprise")
	public String Desactiver_Compte_Entreprise(HttpSession session,String edit_id) {
		int ID_ETR = Integer.parseInt(edit_id);
		System.out.println(ID_ETR);
		responsableMetierImpl.DesactiverCompteEntreprise(ID_ETR);
		return "redirect:Liste_Entreprise";
	}
	
	@PostMapping(value = "/Supprimer_Compte_Entreprise")
	public String Supprimer_Compte_Entreprise(HttpSession session,String delete_id) {
	   int ID_ETR = Integer.parseInt(delete_id);
	   responsableMetierImpl.SupprimerCompteEntreprise(ID_ETR);
	   return "redirect:Liste_Entreprise";
	}
	
	
	/*------------------------------------------------------------------------------------------*/
	
	
	
	@GetMapping(value = "/Liste_Entreprise")
	public String ListeEntreprise(HttpSession session,Model model) {
		Responsable R = (Responsable) session.getAttribute("Responsable");
		Collection<Entreprise> entreprises = responsableMetierImpl.ListeEntreprise(R.getId_res());
		model.addAttribute("entreprises", entreprises);
		model.addAttribute("Responsable",R);
		return "Responsable/Liste_Entreprise_Responsable";
	}
	
	
	@PostMapping(value = "/Ajouter_Entreprise")
	public String Ajouter_Etudiant(HttpSession session, String nom,String adresse,String domaine,String date,String tele ,String mail,String MDP) {
		Responsable R = (Responsable) session.getAttribute("Responsable");
		System.out.println("je suis le resp"+R.getMail_res());
		CompteEntreprise C= (CompteEntreprise) responsableMetierImpl.CompteEntreprise(mail,MDP);
		LocalDate dateTime = LocalDate.parse(date);
		responsableMetierImpl.AjouterEntreprise(nom, domaine, adresse, dateTime , tele , C,R);
		return "redirect:Liste_Entreprise";
	}
	
	@GetMapping(value = "/Logout_Responsable")
	public String Se_Deconnecter(HttpSession session) {
		session.removeAttribute("Responsable");
		return "redirect:/";
	}
}



