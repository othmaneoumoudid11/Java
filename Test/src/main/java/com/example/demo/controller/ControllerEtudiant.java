package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.dao.CVRepository;
import com.example.demo.dao.EcoleRepository;
import com.example.demo.dao.EtudiantRepository;
import com.example.demo.dao.LettreMotivationRepository;
import com.example.demo.entities.CV;
import com.example.demo.entities.CompteEtudiant;
import com.example.demo.entities.Convention;
import com.example.demo.entities.Ecole;
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
import com.example.demo.metiers.EtudiantMetierImpl;
import com.example.demo.metiers.ICompte;
import com.example.demo.metiers.IEcoleMetier;
import com.example.demo.metiers.IEntrepriseMetier;
import com.example.demo.metiers.IEtudiantMetier;

@Controller
@SessionAttributes({ "Etudiant" })
public class ControllerEtudiant {

	@Autowired
	private ICompte compteMetierImpl;
	
	@Autowired
	private IEcoleMetier ecolemetier;
	
	@Autowired
	private IEntrepriseMetier entreprisemetier;

	@Autowired
	private EtudiantRepository etudiantRepository;

	@Autowired
	private EcoleRepository ecoleRepository;
	
	@Autowired
	private  IEtudiantMetier etudiantmetier;
	
	@Autowired
	private  CVRepository cvRepository;
	@Autowired
	LettreMotivationRepository lettreMotivationRepository;
	
	@GetMapping(value = "/Acceuil_Etudiant")
	public String Acceuil(HttpSession session , Model model) {
		Etudiant ET = (Etudiant) session.getAttribute("Etudiant");
		
		int nbreStage = etudiantmetier.nbreStage(ET.getId_etd());
		int nbreDiplome = etudiantmetier.nbreDiplome(ET.getId_etd());
		int nbreProjet = etudiantmetier.nbreProjet(ET.getId_etd());
		int nbreLangue = etudiantmetier.nbreLangue(ET.getId_etd());
		
		model.addAttribute("Etudiant",ET);
		model.addAttribute("nbreStage",nbreStage);
		model.addAttribute("nbreDiplome",nbreDiplome);
		model.addAttribute("nbreProjet",nbreProjet);
		model.addAttribute("nbreLangue",nbreLangue);
		
		int [] nbre_entretien_annee = etudiantmetier.nbr_entretiens_an(ET.getId_etd());
		int [] nbre_stage_annee = etudiantmetier.Nbr_stages_an(ET.getId_etd());
		model.addAttribute("entr0",nbre_entretien_annee[0]);
		model.addAttribute("entr1",nbre_entretien_annee[1]);
		model.addAttribute("entr2",nbre_entretien_annee[2]);
		model.addAttribute("entr3",nbre_entretien_annee[3]);
		model.addAttribute("entr4",nbre_entretien_annee[4]);
		model.addAttribute("entr5",nbre_entretien_annee[5]);
		model.addAttribute("entr6",nbre_entretien_annee[6]);
		
		model.addAttribute("stage0",nbre_stage_annee[0]);
		model.addAttribute("stage1",nbre_stage_annee[1]);
		model.addAttribute("stage2",nbre_stage_annee[2]);
		model.addAttribute("stage3",nbre_stage_annee[3]);
		model.addAttribute("stage4",nbre_stage_annee[4]);
		model.addAttribute("stage5",nbre_stage_annee[5]);
		model.addAttribute("stage6",nbre_stage_annee[6]);
		System.out.println(nbre_entretien_annee[5]+" "+nbre_entretien_annee[1]+" "+nbre_entretien_annee[6]+" "+nbre_stage_annee[5]);
		
		
		return "Etudiant/Acceuil_Etudiant";
	}
	
	@GetMapping(value = "/Liste-OffresEtudiant")
	public String ListeEtudiant(HttpSession session, Model model) {
		System.out.println("hello");
		Etudiant ET = (Etudiant) session.getAttribute("Etudiant");
		Collection<Offre> offres = etudiantmetier.ListerOffresEtudiant(ET.getDom_etd());
		for(Offre o : offres) {System.out.println(o.getDesc_off());}
		System.out.println(ET.getDom_etd());
		model.addAttribute("offres", offres);
		model.addAttribute("Etudiant",ET);
		return "Etudiant/Liste-OffresEtudiant";
	}
	
	@PostMapping("/Postuler_Etudiant")
	public String AjouterFormation(HttpSession session, String delete_id , String description ){
		Etudiant ET = (Etudiant) session.getAttribute("Etudiant");
		int id = Integer.parseInt(delete_id);
		if(etudiantmetier.VerifierPostuler(ET.getId_etd() , id)==0) {
			etudiantmetier.AfficheLettreMotivation(description, id, ET.getId_etd());;
		    etudiantmetier.postuleOffre(id, ET.getId_etd());
		}
		
		return "redirect:Liste-OffresEtudiant";
	}
	

	
	@GetMapping(value = "/Curriculum_vitae")
	public String CurriculumVitae(HttpSession session, Model model) {
		Etudiant ET = (Etudiant) session.getAttribute("Etudiant");
		if(cvRepository.getCVEtudiant(ET.getId_etd())==null) {
			CV cv = new CV();
			model.addAttribute("cv",cv);
			model.addAttribute("Etudiant",ET);
			return "Etudiant/Curriculum_vitae";
			
		}else {
		CV cv = cvRepository.getCVEtudiant(ET.getId_etd());
		model.addAttribute("cv",cv);
		model.addAttribute("Etudiant",ET);
		return "Etudiant/Curriculum_vitae";
		}
		
	}
	
	@PostMapping(value = "/InsertCV")
	public String CreerOffre(HttpSession session, String linkdin, String ghithub, String info,Model model) {
		Etudiant ET = (Etudiant) session.getAttribute("Etudiant");
		
        if(etudiantmetier.VerifierCV(ET.getId_etd())==1) {
        	etudiantmetier.UpdateCV(ET.getId_etd(), linkdin, ghithub, info);
        }else {
        	etudiantmetier.InsererCV(ET.getId_etd(), linkdin, ghithub, info);
        }
		model.addAttribute("Etudiant",ET);
		return "redirect:Curriculum_vitae";
	}
	
	
	@GetMapping(value = "/List_Experience")
	public String ListeExperience(HttpSession session, Model model ){
		Etudiant ET = (Etudiant) session.getAttribute("Etudiant");
		Collection<Experiences> experiences = etudiantmetier.ListExperience(ET.getId_etd());
		model.addAttribute("experiences",experiences);
		model.addAttribute("Etudiant",ET);
	    return "Etudiant/List_Experience";
	}
	
	
	
	@PostMapping(value = "/Ajouter_Experience")
	public String AjouterExperience(HttpSession session, String date_debut, String date_fin, String description, String experience ){
		Etudiant ET = (Etudiant) session.getAttribute("Etudiant");
		CV cv = cvRepository.getCVEtudiant(ET.getId_etd());
		etudiantmetier.AjouterExperience( cv.getId_cv(), date_debut, date_fin,description,experience);
		System.out.println(date_debut);
		return "redirect:List_Experience";
	}
	
	

	@GetMapping(value = "/List_Hobbie")
	public String ListeHobbie(HttpSession session, Model model){
		Etudiant ET = (Etudiant) session.getAttribute("Etudiant");
		Collection<Hobbies> hobbies = etudiantmetier.ListHobbies(ET.getId_etd());
		model.addAttribute("hobbies",hobbies);
		model.addAttribute("Etudiant",ET);
	    return "Etudiant/List_Hobbie";
	}
	

	@PostMapping(value = "/Ajouter_Loisir")
	public String AjouterLoisir(HttpSession session, String loisir ){
		Etudiant ET = (Etudiant) session.getAttribute("Etudiant");
		CV cv = cvRepository.getCVEtudiant(ET.getId_etd());
	    etudiantmetier.AjouteHobie(cv.getId_cv(), loisir);
		return "redirect:List_Hobbie";
	}
	
	@GetMapping(value = "/List_Langue")
	public String ListeLangue(HttpSession session, Model model ){
		Etudiant ET = (Etudiant) session.getAttribute("Etudiant");
		Collection<Langues> langues = etudiantmetier.ListLangues(ET.getId_etd());
		model.addAttribute("langues",langues);
		model.addAttribute("Etudiant",ET);
	    return "Etudiant/List_Langue";
	}
	
	@PostMapping(value = "/Ajouter_Langue")
	public String AjouterLangue(HttpSession session, String nom, String niveau ){
		Etudiant ET = (Etudiant) session.getAttribute("Etudiant");
		CV cv = cvRepository.getCVEtudiant(ET.getId_etd());
	    etudiantmetier.AjouteLangue(cv.getId_cv(), nom, niveau);
	    System.out.println(nom+niveau);
		return "redirect:List_Langue";
	}
	
	@GetMapping(value = "/List_Projet")
	public String ListeProjet(HttpSession session , Model model){
		Etudiant ET = (Etudiant) session.getAttribute("Etudiant");
		Collection<ProjetsReal> projetreals = etudiantmetier.ListProjet(ET.getId_etd());
		model.addAttribute("projetreals",projetreals);
		model.addAttribute("Etudiant",ET);
	    return "Etudiant/List_Projet";
	}
	
	
	@PostMapping(value = "/Ajouter_Projet")
	public String AjouterProjet(HttpSession session, String sujet , String dateSujet , String description ){
		Etudiant ET = (Etudiant) session.getAttribute("Etudiant");
		CV cv = cvRepository.getCVEtudiant(ET.getId_etd());
		etudiantmetier.AjouteProjetsRealiser( cv.getId_cv(), dateSujet, description, sujet);
		return "redirect:List_Projet";
	}
	
	@GetMapping(value = "/List_Formation")
	public String ListeFormation(HttpSession session , Model model){
		Etudiant ET = (Etudiant) session.getAttribute("Etudiant");
		Collection<Formation> formations = etudiantmetier.ListFormation(ET.getId_etd());
		System.out.println(formations.toString());
		model.addAttribute("formations",formations);
		model.addAttribute("Etudiant",ET);
	    return "Etudiant/List_Formation";
	}

	@PostMapping(value = "/Ajouter_Formation")
	public String AjouterFormation(HttpSession session, String formation, String source, String date_debut, String date_fin){
		Etudiant ET = (Etudiant) session.getAttribute("Etudiant");
		CV cv = cvRepository.getCVEtudiant(ET.getId_etd());
	    etudiantmetier.AjouteFormation(cv.getId_cv(), date_debut, date_fin,formation,  source);
		return "redirect:List_Formation";
	}
	
	@GetMapping(value = "/Liste_Entretien")
	public String ListeEntretien(HttpSession session , Model model){
		Etudiant ET = (Etudiant) session.getAttribute("Etudiant");
		Collection<Entretien> entretiens = etudiantmetier.ListeEntretien(ET.getId_etd());
		model.addAttribute("entretiens",entretiens);
		model.addAttribute("Etudiant",ET);
	    return "Etudiant/Liste_Entretien";
	}

	@PostMapping(value = "/Accepter_Entretien")
	public String AccepterEntretien(HttpSession session,String delete_id){
		Etudiant ET = (Etudiant) session.getAttribute("Etudiant");
		int id = Integer.parseInt(delete_id);
		etudiantmetier.AccepteEntretirn(id);
		return "redirect:Liste_Entretien";
	}
	
	@PostMapping(value = "/Refuser_Entretien")
	public String RefuserEntretien(HttpSession session,String delete_id){
		Etudiant ET = (Etudiant) session.getAttribute("Etudiant");
		int id = Integer. parseInt(delete_id);
		etudiantmetier.RefuserEntretien(id);
		return "redirect:Liste_Entretien";
	}
	
	
	@GetMapping(value = "/Liste_Offre_Convention")
	public String Liste_Offre_Convention(HttpSession session,Model model){
		Etudiant ET = (Etudiant) session.getAttribute("Etudiant");
		Collection<Entretien> entretiens = etudiantmetier.ListeEntretienValider(ET.getId_etd());
		model.addAttribute("entretiens",entretiens);
		model.addAttribute("Etudiant",ET);
		return "Etudiant/Liste_Offre_Convention";
	}
	
	

	
	@PostMapping(value = "/Demande_Convention")
	public String Demande_Convention(HttpSession session,Model model,String delete_id){
		Etudiant ET = (Etudiant) session.getAttribute("Etudiant");
		int id_off = Integer. parseInt(delete_id);
		etudiantmetier.DemandeConvention( ET.getId_etd() , id_off);
		return "redirect:Liste_Offre_Convention";
	}
	
	
	@GetMapping(value = "/Liste_Convention_Accepter")
	public String Liste_Convention_Accepter(HttpSession session,Model model){
		Etudiant ET = (Etudiant) session.getAttribute("Etudiant");
		Collection<Convention> conventions = etudiantmetier.ConventionsEtudiant(ET.getId_etd());;
		model.addAttribute("conventions",conventions);
		return "Etudiant/Liste_Convention_Accepter";
	}
	
	
	@PostMapping(value = "/Lire_Convention")
	public String Lire_Convention(HttpSession session,Model model, String id_conv){
		Etudiant ET = (Etudiant) session.getAttribute("Etudiant");
		int id = Integer.parseInt(id_conv);
		Convention convention = etudiantmetier.GetConvention(id);
		model.addAttribute("convention",convention);
		model.addAttribute("Etudiant",ET);
		return "Etudiant/Lire_Convention";
	}
	
	@GetMapping(value = "/Liste-Stage-Etudiant")
	public String ListeStageEtudiant(HttpSession session,Model model){
		Etudiant ET = (Etudiant) session.getAttribute("Etudiant");
		Collection<Stage> stages = etudiantmetier.ListeStageEtudiant(ET.getId_etd());
		model.addAttribute("stages",stages);
		return "Etudiant/Liste-Stage-Etudiant";
	}
	
	@GetMapping(value = "/Logout_Etudiant")
	public String Se_Deconnecter(HttpSession session) {
		System.out.println("avant de supprimer");
		session.removeAttribute("Etudiant");
		return "redirect:/";
	}
	
	
	@GetMapping(value = "/NonStage")
	public String NonStage(HttpSession session) {
		Etudiant ET = (Etudiant) session.getAttribute("Etudiant");
		etudiantmetier.NonStage(ET.getId_etd());
		ET.setStage_tr(false);
		return "redirect:/Acceuil_Etudiant";
	}


	@GetMapping(value = "/StageTrouve")
	public String StageTrouve(HttpSession session) {
		Etudiant ET = (Etudiant) session.getAttribute("Etudiant");
		etudiantmetier.StageTrouve(ET.getId_etd());
		ET.setStage_tr(true);
		return "redirect:/Acceuil_Etudiant";
	}
	
}
