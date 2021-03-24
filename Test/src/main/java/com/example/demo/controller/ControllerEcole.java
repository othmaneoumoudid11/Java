package com.example.demo.controller;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.dao.CVRepository;
import com.example.demo.dao.CompteRepository;
import com.example.demo.dao.ConventionRepository;
import com.example.demo.dao.EcoleRepository;
import com.example.demo.dao.EntretienRepository;
import com.example.demo.dao.EtudiantRepository;
import com.example.demo.dao.LettreMotivationRepository;
import com.example.demo.dao.StageRepository;
import com.example.demo.entities.Compte;
import com.example.demo.entities.CompteAdminEcole;
import com.example.demo.entities.CompteEntreprise;
import com.example.demo.entities.CompteEntrepriseInterne;
import com.example.demo.entities.CompteEtudiant;
import com.example.demo.entities.Convention;
import com.example.demo.entities.Ecole;
import com.example.demo.entities.Entreprise;
import com.example.demo.entities.Etudiant;
import com.example.demo.entities.Filiere;
import com.example.demo.entities.LettreMotivation;
import com.example.demo.entities.Stage;
import com.example.demo.metiers.EcoleMetierImpl;
import com.example.demo.metiers.ICompte;
import com.example.demo.metiers.IEcoleMetier;
import com.example.demo.metiers.IEntrepriseMetier;

@Controller
@SessionAttributes({ "Ecole" })
public class ControllerEcole {

	@Autowired
	private ICompte compteMetierImpl;
	@Autowired
	EntretienRepository entretienRepository;
	
	@Autowired
	private ConventionRepository conventionRepository;
	
	@Autowired
	private IEcoleMetier ecolemetier;
	@Autowired
	StageRepository stageRepository;
	
	@Autowired
	private IEntrepriseMetier entreprisemetier;

	@Autowired
	private EtudiantRepository etudiantRepository;

	@Autowired
	private EcoleRepository ecoleRepository;

	@Autowired
	private CompteRepository compterepository;
	
	@Autowired
	private LettreMotivationRepository lettreMotivationRepository;
	
	@Autowired
	private CVRepository cvRepository;
	
	@GetMapping(value = "/Acceuil_Ecole")
	public String Acceuil(HttpSession session,Model model) {
		Ecole E = (Ecole) session.getAttribute("Ecole");
		int CountEtd = ecolemetier.countEtudiant(E.getId_eco());
		int CountEtdStage = ecolemetier.countEtudiantStagiaire(E.getId_eco());
		model.addAttribute("CountEtdStage",CountEtdStage);
		int CountEntreprise = ecolemetier.countEntreprise();
		model.addAttribute("CountEntreprise",CountEntreprise);
		int CountEtudiantSansStage= ecolemetier.CountEtudiantSansStage(E.getId_eco());
		model.addAttribute("CountEtudiantSansStage",CountEtudiantSansStage);
		model.addAttribute("CountEtd",CountEtd);
		model.addAttribute("ecole", E);
		return "Ecole/Acceuil_Ecole";
	}
	
	// les modifications apporter aux etudiants-----------------------------------------------------------
	
	@GetMapping(value = "/Liste-Etudiant")
	public String ListeEtudiant(HttpSession session, Model model) {
		Ecole E = (Ecole) session.getAttribute("Ecole");
		Collection<Etudiant> etudiants = ecolemetier.ListeEtudiant(E.getId_eco());
		Collection<Filiere> filieres = ecolemetier.ListeFiliere(E.getId_eco());
		model.addAttribute("filieres", filieres);
		model.addAttribute("etudiants", etudiants);
		model.addAttribute("ecole", E);
		return "Ecole/Liste-Etudiants";
	}
	
	@PostMapping(value = "/Ajouter_Etudiant")
	public String Ajouter_Etudiant(HttpSession session, String First_name, String Last_name , String Email ,String phone,String filiere, String domaine) {
		Ecole ecole = (Ecole) session.getAttribute("Ecole");
		int id_filiere = Integer. parseInt(filiere);
		ecolemetier.Ajouter_Etudiant(ecole,  Last_name,  First_name,  Email,  phone,  domaine, id_filiere);
		return "redirect:Liste-Etudiant";
	}

	@PostMapping(value = "/Modifier_etudiant")
	public String Modifier_Etudiant(HttpSession session, String first_name, String last_name , String email ,String phone,String filiere, String domaine, String edit_id) {
		int id = Integer. parseInt(edit_id);
		int id_filiere = Integer. parseInt(filiere);
		ecolemetier.ModifierEtudiant(first_name, last_name, email, phone, id_filiere, domaine, id);
		return "redirect:Liste-Etudiant";
	}
	
	@PostMapping(value = "/Supprimer_Etudiant")
	public String Supprimer_Etudiant(HttpSession session, String delete_id) {
	   int id_etd = Integer.parseInt(delete_id);
	   Etudiant E = etudiantRepository.select_Etudiant(id_etd);
	   
	   for (Stage S: E.getStages()) {
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
	  
	   
	   if((cvRepository.getCVEtudiant(E.getId_etd()))!=null) {
		   ecolemetier.Supprimer_Etudiant_cv(cvRepository.getCVEtudiant(E.getId_etd()).getId_cv());
	   }
	  
	  
	   etudiantRepository.deleteById(id_etd);
	   if(E.getCompte()!=null) {
		    compterepository.deleteById(E.getCompte().getId_comp());
	   }
	   return "redirect:Liste-Etudiant";
	   
	}
	
	//--------------------------------------------------------------------------------------------------------
	//Liste-Etudiants-Sans-Stage
	@GetMapping(value = "/Liste-Etudiant-Sans-Stage")
	public String ListeEtudiantSansStage(HttpSession session, Model model) {
		Ecole E = (Ecole) session.getAttribute("Ecole");
		Collection<Etudiant> etudiants = ecolemetier.ConsulterEtudiantsNStage(E.getId_eco());
		model.addAttribute("etudiants", etudiants);
		model.addAttribute("ecole", E);
		return "Ecole/Liste-Etudiant-Sans-Stage";
	}
	
	//liste des entreprise
		@GetMapping(value = "/Liste-Entreprise")
		public String ListeEntreprise(HttpSession session, Model model) {
			Collection<Entreprise> entreprises = ecolemetier.ListeEntreprise();
			Ecole E = (Ecole) session.getAttribute("Ecole");
			model.addAttribute("entreprises", entreprises);
			model.addAttribute("ecole", E);
			return "Ecole/Liste-Entreprise";
		}
	
	@GetMapping(value = "/Compte_Etudiant")
	public String CompteEtudiant(HttpSession session, Model model) {
		Ecole E = (Ecole) session.getAttribute("Ecole");
		Collection<Etudiant> etudiants = ecolemetier.EtudiantsSansCompte(E.getId_eco());
		model.addAttribute("etudiants", etudiants);
		model.addAttribute("ecole", E);
		return "Ecole/Compte-Etudiant";
	}
	
	@PostMapping(value = "/Activer_Compte")
	public String ActiverCompte(HttpSession session, String delete_id) {
		Ecole E = (Ecole) session.getAttribute("Ecole");
		int id = Integer.parseInt(delete_id);
	    ecolemetier.InscrireEtudiant(E.getId_eco(), id);
	    return "redirect:Compte_Etudiant";	
	}

	@GetMapping(value = "/Liste_Convention")
	public String Liste_Convention(HttpSession session, Model model) {
		Ecole E = (Ecole) session.getAttribute("Ecole");
		Collection<Etudiant> etudiants = ecolemetier.EtudiantsSansCompte(E.getId_eco());
		model.addAttribute("etudiants", etudiants);
		model.addAttribute("ecole", E);
		return "Ecole/Liste_Convention";
	} 
	
	
	
	@GetMapping(value = "/Liste_Convention_Etudiant")
	public String Liste_Convention_Etudiant(HttpSession session, Model model) {
		Ecole E = (Ecole) session.getAttribute("Ecole");
		Collection<Convention> conventions = ecolemetier.ConventionsEcole(E.getId_eco());
		model.addAttribute("conventions", conventions);
		model.addAttribute("ecole", E);
		return "Ecole/Liste_Convention_Etudiant";
	} 
	
	
	@PostMapping(value = "/Accepter_SujetStage")
	public String Accepter_SujetStage(HttpSession session, Model model, String conv_id) {
		int id = Integer.parseInt(conv_id);
		Ecole E = (Ecole) session.getAttribute("Ecole");
		ecolemetier.AccepterSujet(id);
		Convention C = conventionRepository.findById(id).orElse(null);
		entreprisemetier.AjouteStage( C.getEtudiant().getId_etd(),C.getOffre().getId_off(), C.getOffre().getEntreprise().getId_entr());
		ecolemetier.ConventionAttribuer(C.getEtudiant().getId_etd(),C.getOffre().getId_off());
		ecolemetier.Valid_cand(C.getEtudiant().getId_etd(),C.getOffre().getId_off());
		return "redirect:Liste_Convention_Etudiant";
	} 
	
	
	
	@PostMapping(value = "/Refuser_Stage")
	public String Refuser_Stage(HttpSession session, Model model, String id_conv) {
		int id = Integer.parseInt(id_conv);
		Ecole E = (Ecole) session.getAttribute("Ecole");
		Convention C = conventionRepository.findById(id).orElse(null);
		ecolemetier.Non_Valid_cand(C.getEtudiant().getId_etd(),C.getOffre().getId_off());
		ecolemetier.acc0(C.getId_conv());
		return "redirect:Liste_Convention_Etudiant";
	} 
	
	
	@GetMapping(value = "/Logout_Ecole")
	public String Se_Deconnecter(HttpSession session) {
		System.out.println("avant de supprimer");
		session.removeAttribute("Ecole");
		return "redirect:/";
	}
	
	@GetMapping(value = "/Liste-Filiere")
	public String ListeFiliere(HttpSession session,Model model) {
		Ecole E = (Ecole) session.getAttribute("Ecole");
		Collection<Filiere> filieres = ecolemetier.ListeFiliere(E.getId_eco());
		model.addAttribute("filieres", filieres);
		model.addAttribute("ecole", E);
		return "Ecole/Liste_Filiere";
	}
	
	
	@PostMapping(value = "/Ajouter_Filiere")
	public String Ajouter_Filiere(HttpSession session, Model model, String filiere , String abreviation) {
		Ecole E = (Ecole) session.getAttribute("Ecole");
		ecolemetier.Ajouter_Filiere(E,filiere,abreviation);
		return "redirect:Liste-Filiere";
	}
	
	

	@PostMapping(value = "/Modifier_filiere")
	public String Modifier_filiere(HttpSession session, Model model,String edit_id, String filiere , String abreviation) {
		Ecole E = (Ecole) session.getAttribute("Ecole");
		int id = Integer.parseInt(edit_id);
		ecolemetier.Modifier_filiere(id,filiere,abreviation);
		return "redirect:Liste-Filiere";
	}
	
	
	@PostMapping(value = "/Supprimer_Filiere")
	public String Supprimer_Filiere(HttpSession session, Model model,String delete_id) {
		Ecole E = (Ecole) session.getAttribute("Ecole");
		int id = Integer.parseInt(delete_id);
		ecolemetier.Supprimer_filiere(id);
		return "redirect:Liste-Filiere";
	}
	
	
}

