package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dao.EcoleRepository;
import com.example.demo.dao.EntrepriseRepository;
import com.example.demo.dao.EtudiantRepository;
import com.example.demo.dao.ResponsableRepository;
import com.example.demo.entities.Compte;
import com.example.demo.entities.CompteAdminEcole;
import com.example.demo.entities.CompteEntreprise;
import com.example.demo.entities.CompteEntrepriseInterne;
import com.example.demo.entities.CompteEtudiant;
import com.example.demo.entities.Ecole;
import com.example.demo.entities.Entreprise;
import com.example.demo.entities.Etudiant;
import com.example.demo.entities.Responsable;
import com.example.demo.metiers.ICompte;
import com.example.demo.metiers.IEcoleMetier;
import com.example.demo.metiers.IEntrepriseMetier;
import com.example.demo.metiers.ResponsableMetierImpl;


@Controller
public class ControllerLogin {
	
	@Autowired
	private ICompte compteMetierImpl;
	
	@Autowired
	private IEcoleMetier ecolemetier;
	
	@Autowired
	private IEntrepriseMetier entreprisemetier;

	@Autowired
	private ResponsableMetierImpl responsableMetierImpl;
	
	@Autowired
	private EtudiantRepository etudiantRepository;

	@Autowired
	private EcoleRepository ecoleRepository;
	
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	
	@Autowired
	private ResponsableRepository responsableRepository;
	
	@GetMapping(value = "/")
	public String index() {
		return "page-login";
	}

	
	@PostMapping(value = "/VerifierLogin")
	public String VerifierLogin(HttpSession session, String email, String MDP) {
		Compte C = compteMetierImpl.login(email, MDP);
		Responsable R =responsableMetierImpl.LoginResponsable(email, MDP); 
		if (C instanceof CompteAdminEcole && C.isActive()) {
			Ecole ecole = ecoleRepository.EcoleCompte(C.getId_comp());
			session.setAttribute("Ecole", ecole);
			return "redirect:Acceuil_Ecole";
		} else if (C instanceof CompteEtudiant ) {
			Etudiant etudiant = etudiantRepository.EtudiantCompte(C.getId_comp());
	        session.setAttribute("Etudiant", etudiant);
	        System.out.println(etudiant.getDom_etd());
			return "redirect:Acceuil_Etudiant";

		}else if (C instanceof CompteEntreprise && C.isActive()) {
			Entreprise entreprise = entrepriseRepository.EntrepriseCompte(C.getId_comp());
		    session.setAttribute("Entreprise", entreprise);
		    return "redirect:Acceuil_Entreprise";
		    }
		else if (C instanceof CompteEntrepriseInterne) {
			Entreprise entreprise = entrepriseRepository.EntrepriseCompte(C.getId_comp());
		    session.setAttribute("Entreprise", entreprise);
		    return "redirect:Acceuil_Entreprise";
		}else if (R instanceof Responsable ) {
	        session.setAttribute("Responsable", R);
	        return "redirect:Acceuil_Responsable";
		}
		return "page-error";
	}
	
	
	
}
