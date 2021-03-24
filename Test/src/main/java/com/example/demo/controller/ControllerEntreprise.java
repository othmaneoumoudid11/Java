package com.example.demo.controller;

import java.text.SimpleDateFormat;
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

import com.example.demo.dao.EcoleRepository;
import com.example.demo.dao.EntretienRepository;
import com.example.demo.dao.EtudiantRepository;
import com.example.demo.dao.OffreRepository;
import com.example.demo.entities.CV;
import com.example.demo.entities.Compte;
import com.example.demo.entities.CompteAdminEcole;
import com.example.demo.entities.CompteEntreprise;
import com.example.demo.entities.CompteEntrepriseInterne;
import com.example.demo.entities.CompteEtudiant;
import com.example.demo.entities.*;
import com.example.demo.metiers.EntrepriseMetierImpl;
import com.example.demo.metiers.ICompte;
import com.example.demo.metiers.IEcoleMetier;
import com.example.demo.metiers.IEntrepriseMetier;

@Controller
@SessionAttributes({ "Entreprise"})

public class ControllerEntreprise {
	
	
	@Autowired
	IEntrepriseMetier entrepriseMetierImpl;
	@Autowired
	EtudiantRepository etudiantRepository;
	
	@GetMapping(value = "/Acceuil_Entreprise")
	public String Acceuil(HttpSession session,Model model) {
		Entreprise entreprise = (Entreprise) session.getAttribute("Entreprise");
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
	    model.addAttribute("entreprise", entreprise);
		return "Entreprise/Acceuil_Entreprise";
	}

	@GetMapping(value = "/Liste_Offre")
	public String ListeOffre(HttpSession session,Model model) {
		Entreprise entreprise = (Entreprise) session.getAttribute("Entreprise");
		Collection<Offre> offres = entrepriseMetierImpl.ConsulterOffres(entreprise.getId_entr());
	    model.addAttribute("offres", offres);
	    model.addAttribute("entreprise", entreprise);
		return "Entreprise/Liste_Offre";
	}
	
	
	@PostMapping(value = "/CreerOffre")
	public String CreerOffre(HttpSession session, String domaine, String sujet, String duree, String competences , String description , Model model) {
		Entreprise entreprise = (Entreprise) session.getAttribute("Entreprise");
	    model.addAttribute("entreprise", entreprise);
		entrepriseMetierImpl.CreeOffre(entreprise.getId_entr(), domaine, new Date(), description, competences, duree,  sujet);
		return "redirect:Liste_Offre";
		
	}
	
	@GetMapping(value = "/Liste_Compte_Interne")
	public String ListeCompteInterne(HttpSession session, Model model) {
		Entreprise entreprise = (Entreprise) session.getAttribute("Entreprise");
	    model.addAttribute("entreprise", entreprise);
		Collection<CompteEntrepriseInterne> CEI = entrepriseMetierImpl.ListeCompteInterne(entreprise.getId_entr());
		model.addAttribute("CEI" , CEI);
		return "Entreprise/Liste_Compte_Interne";
	}
	
	@PostMapping(value = "/Supprimer_Compte_Interne")
	public String Supprimer_Compte_Interne(HttpSession session, String delete_id) {
		Entreprise entreprise = (Entreprise) session.getAttribute("Entreprise");
		int id = Integer.parseInt(delete_id);
	    entrepriseMetierImpl.SupprimerCompteInterne(id);
	    return "redirect:Liste_Compte_Interne";	
	}

	
	@PostMapping(value = "/Ajouter_Compte_Interne")
	public String AjouterCompteInterne(HttpSession session,  String email ,String MDP) {
		Entreprise entreprise = (Entreprise) session.getAttribute("Entreprise");
	    entrepriseMetierImpl.CreeCompteIntern(entreprise.getId_entr(), email, MDP);
	    return "redirect:Liste_Compte_Interne";	
	}
	
	
	@GetMapping(value = "/Liste_Etudiant_Postuler_Offre")
	public String AjouterCompteInterne(HttpSession session,  String id_off, Model model) {
		Entreprise entreprise = (Entreprise) session.getAttribute("Entreprise");
		int id = Integer.parseInt(id_off);
		Collection<Etudiant> etudiants = entrepriseMetierImpl.ListEtudiantPostuler(id);
		model.addAttribute("etudiants" , etudiants);
	    model.addAttribute("entreprise", entreprise);
		model.addAttribute("id_off",id);
	    return "Entreprise/Liste_Etudiant_Postuler_Offre";	
	}
	
	
	@PostMapping(value = "/CVEtudiant")
	public String CVEtudiant(HttpSession session , Model model , String id_etd){
		Entreprise entreprise = (Entreprise) session.getAttribute("Entreprise");
		int id = Integer.parseInt(id_etd);
		CV cv = entrepriseMetierImpl.CVEtudiant(id);
		Collection<Experiences> ex = entrepriseMetierImpl.CVEtudiantExperience(id);
		Collection<Formation> fr = entrepriseMetierImpl.CVEtudiantFormation(id);
		Collection<ProjetsReal> pr = entrepriseMetierImpl.CVEtudiantProjet(id);
		Collection<Hobbies> hb = entrepriseMetierImpl.CVEtudiantHobbies(id);
		Collection<Langues> lg = entrepriseMetierImpl.CVEtudiantLangues(id);
	    model.addAttribute("entreprise", entreprise);
		model.addAttribute("Cv",cv);
		model.addAttribute("Experiences",ex);
		model.addAttribute("Formation",fr);
		model.addAttribute("ProjetsReal",pr);
		model.addAttribute("Hobbies",hb);
		model.addAttribute("Langues",lg);
	    return "Entreprise/CVEtudiant";
	}
	
	
	
	@PostMapping(value = "/Appel_Candidat")
	public String Appel_Candidat(HttpSession session , Model model , String id_etd,String id_off ,String date ){
		Entreprise entreprise = (Entreprise) session.getAttribute("Entreprise");
		int id_e = Integer.parseInt(id_etd);
		int id_o = Integer.parseInt(id_off);
		LocalDateTime dateTime = LocalDateTime.parse(date);
	    entrepriseMetierImpl.ConvocationEntre(id_e, id_o,entreprise.getId_entr() , dateTime);
	    entrepriseMetierImpl.RemovePostule(id_e, id_o);
	    Collection<Etudiant> etudiants = entrepriseMetierImpl.ListEtudiantPostuler(id_o);
	    model.addAttribute("entreprise", entreprise);
		model.addAttribute("etudiants" , etudiants);
		model.addAttribute("id_off",id_o);
		return "Entreprise/Liste_Etudiant_Postuler_Offre";	
	}
	
	
	
	@PostMapping(value = "/LettreEtudiant")
	public String LettreEtudiant(HttpSession session , Model model , String id_etd,String id_off){
		Entreprise entreprise = (Entreprise) session.getAttribute("Entreprise");
		int id_e = Integer.parseInt(id_etd);
		int id_o = Integer.parseInt(id_off);
		LettreMotivation L = entrepriseMetierImpl.Lettre_motivation(id_e,id_o);
		model.addAttribute("entreprise", entreprise);
		model.addAttribute("L",L);
	    return "Entreprise/LettreEtudiant";
	}
	
	@GetMapping(value = "/Liste_Stagiaire")
	public String Liste_Stagiaire(HttpSession session, Model model) {
		Entreprise entreprise = (Entreprise) session.getAttribute("Entreprise");
		Collection<Entretien> entretiens = entrepriseMetierImpl.ListeStagiaire(entreprise.getId_entr());
		model.addAttribute("entretiens", entretiens);
		model.addAttribute("entreprise",entreprise);
		return "Entreprise/Liste_Stagiaire";
	}
	
	
	
	@PostMapping(value = "/Lire_Convention_Entreprise")
	public String Lire_Convention_Entreprise(HttpSession session , Model model , String id_etd,String id_off){
		Entreprise entreprise = (Entreprise) session.getAttribute("Entreprise");
		int id_e = Integer.parseInt(id_etd);
		int id_o = Integer.parseInt(id_off);
		Convention C = new Convention();
		C = entrepriseMetierImpl.ConventionEtudiant(id_e,id_o);
		if(C != null) {
		System.out.println(C.getId_conv());
		 model.addAttribute("entreprise", entreprise);
		model.addAttribute("convention",C);
	    return "Entreprise/Lire_Convention";
	    }else {
	    	 return "redirect:Lire_Convention_Entreprise";
	    }
	}
	
	
	
	@GetMapping(value = "/Liste_Entretien_Entreprise")
	public String Liste_Entretien_Entreprise(HttpSession session , Model model){
		Entreprise entreprise = (Entreprise) session.getAttribute("Entreprise");
		Collection<Entretien> E = entrepriseMetierImpl.ListEntretientsEntreprise(entreprise.getId_entr());
		model.addAttribute("entreprise", entreprise);
		model.addAttribute("entretiens",E);
	    return "Entreprise/Liste_Entretien_Entreprise";
	}
	
	
	@PostMapping(value = "/Valider_Condidature")
	public String Valider_Condidature(HttpSession session, String id_etd,String id_off,String id_etr){
		Entreprise entreprise = (Entreprise) session.getAttribute("Entreprise");
		int id_et = Integer.parseInt(id_etd);
		int id_er = Integer.parseInt(id_etr);
		int id_of = Integer.parseInt(id_off);
		entrepriseMetierImpl.AjouteStage(id_et,id_of, entreprise.getId_entr());
		entrepriseMetierImpl.Valider_Condidature(id_er);
		//entrepriseMetierImpl.Stage_Trouver(id_et);
	    return "redirect:Liste_Entretien_Entreprise";
	}
	
	
	
	
	@PostMapping(value = "/Refuser_Etudiant")
	public String Refuser_Etudiant(HttpSession session, String id_etr){
		Entreprise entreprise = (Entreprise) session.getAttribute("Entreprise");
		int id_et = Integer.parseInt(id_etr);
		entrepriseMetierImpl.acc0(id_et);
		//entrepriseMetierImpl.Stage_Trouver(id_et);
	    return "redirect:Liste_Entretien_Entreprise";
	}
	
	
	
	@PostMapping(value = "/Valider_Convention")
	public String Valider_Convention(HttpSession session, String id_etd,String id_off,String id_etr){
		int id_er = Integer.parseInt(id_etr);
		entrepriseMetierImpl.Valider_Convention(id_er);
	    return "redirect:Liste_Entretien_Entreprise";
	}
	
	
	@PostMapping(value = "/Noter_Stage")
	public String Noter_Stage(HttpSession session, String id_etd, String id_off, String note){
		Entreprise entreprise = (Entreprise) session.getAttribute("Entreprise");
		int id_e = Integer.parseInt(id_etd);
		int id_of = Integer.parseInt(id_off);
		double not = Double.parseDouble(note);
		System.out.println(id_e+"   "+id_of+"    "+not);
		entrepriseMetierImpl.AjouteNoteStage(id_e,not,id_of);
		entrepriseMetierImpl.Stage_Fini(id_e);
		entrepriseMetierImpl.ArchiverEtudiant(id_e , id_of);
	    return "redirect:Liste_Stagiaire";
	}
	
	@GetMapping(value = "/Logout_Entreprise")
	public String Se_Deconnecter(HttpSession session) {
		session.removeAttribute("Entreprise");
		return "redirect:/";
	}
//	
}
