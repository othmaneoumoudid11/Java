package com.example.demo;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.demo.dao.*;
import com.example.demo.entities.*;
import com.example.demo.metiers.*;

@SpringBootApplication
public class TestApplication implements CommandLineRunner  {

	  @Autowired CompteRepository compteRepository;
	  
	  @Autowired EtudiantRepository etudiantRepository;
	  
	  @Autowired EcoleRepository ecoleRepository;
	  
	  @Autowired EcoleMetierImpl ecoleMetierImpl;
	  
	  @Autowired EntrepriseMetierImpl entrepriseMetierImpl;
	  
	  @Autowired OffreRepository offreRepository;
	  
	  @Autowired EntrepriseRepository entrepriseRepository;
	  
	  @Autowired ConventionRepository conventionRepository;
	  
	  @Autowired ICompte comptemetier;
	  
	  @Autowired
	  IEtudiantMetier etudiantMetier;
	  
	  
	 
	  @Autowired
	  CVRepository cvRepository;
	  

	  @Autowired
	  ResponsableRepository responsableRepository;

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	
	  @Override public void run(String... args) throws Exception {
	  
			/*
			 * compteRepository.save(new CompteAdmin("Admin@gmail.com", "1111", false));
			 * compteRepository.save(new CompteResponsable("Responsable@gmail.com", "1111",
			 * false)); compteRepository.save(new CompteEtudiant("Etudiant@gmail.com",
			 * "1111", false)); compteRepository.save(new
			 * CompteEntrepriseInterne("EntrepriseInterne@gmail.com", "1111", true));
			 */
		
		  
		  
		  
		  
		  
//		  Compte CE=compteRepository.save(new CompteEntreprise("Entreprise@gmail.com","1111", false)); 
//		  Compte CAE = compteRepository.save(new CompteAdminEcole("AdminEcole@gmail.com", "1111", false)); 
//		  Ecole ecole=ecoleRepository.save(new Ecole(CAE,"ENSIAS","RABAT","ENSIAS@gmail.com","08773838383","Addresse1")); 
//		  Entreprise E = entrepriseRepository.save(new Entreprise("New Dev","Devellopement" , "Fes" , new Date(), "0928292",CE));
		 
		  
		  
		  
		  
		  
		  
		  
		  
	  
	 /* Offre O = offreRepository.save(new Offre(E,"Dev",new
	  Date(),"des","miss","comp","durr","fes","sujet")); Etudiant ETD =
	  etudiantRepository.save(new Etudiant(ecole , "zohair" , "moufakkir" ,
	  "zohair@gmail.com","09877666", "Dev" , "GL",false)); Etudiant ETD2 =
	  etudiantRepository.save(new Etudiant(ecole , "othmane" , "oumoudid" ,
	  "zohair@gmail.com","09877666", "Dev" , "GL",true)); CV cv =
	  cvRepository.save(new CV(ETD, "", "", "", "")); CV cv2 =
	  cvRepository.save(new CV(ETD2, "", "", "", ""));
	  
	  
	  
	  LettreMotivation lettre = lettreMotivationRepository.save(new
	  LettreMotivation("contenu",cv,O)); Postuler P = postulerRepository.save(new
	  Postuler(ETD,O,lettre,"Path")); LettreMotivation lettre1 =
	  lettreMotivationRepository.save(new LettreMotivation("contenu",cv2,O));
	  Postuler P2 = postulerRepository.save(new Postuler(ETD2,O,lettre,"Path"));
	  
	  Collection<Etudiant> etu = entrepriseMetierImpl.ConsulterListePostule(E, O);
	  for(Etudiant et : etu) { System.out.println(et.getNom_etd()); }
	  
	  
	  
	  ecoleMetierImpl.InscrireEtudiant(ETD, "222");
	  ecoleMetierImpl.InscrireEtudiant(ETD2, "199");
	  
	  Collection<Etudiant> Col = ecoleMetierImpl.ConsulterEtudiants() ;
	  for(Etudiant e : Col) { System.out.println(e.getNom_etd()); } Etudiant et =
	  ecoleMetierImpl.TrouveEtudiant(ETD); System.out.println(et.getNom_etd());
	  Etudiant et1 = ecoleMetierImpl.TrouveEtudiant(ETD2);
	  System.out.println(et1.getNom_etd());
	  
	  
	  
	  CompteAdminEcole CAE =compteRepository.save(new
	  CompteAdminEcole("AdminEcole@gmail.com", "1111", false)); Ecole
	  ecole=ecoleRepository.save(new Ecole(CAE,"ENSIAS","RABAT","ENSIAS@gmail.com"
	  ,"08773838383","Addresse1")); Etudiant E = etudiantRepository.save(new
	  Etudiant(ecole , "othmane" , "oumoudid" ,"zohair@gmail.com","09877666", "Dev"
	  , "GL",false));
	  ecoleMetierImpl.InscrireEtudiant(ecole.getId_eco(),E.getId_etd(), "111");
	  Collection<Etudiant> etudiants =
	  ecoleMetierImpl.ConsulterEtudiantsNStage(ecole.getId_eco()); // for(Etudiant
	  e : etudiants) { // System.out.println(e.getNom_etd()); // }
	  
	  
	  CompteEntreprise CE=compteRepository.save(new
	  CompteEntreprise("Entreprise@gmail.com","1111", false));
	  
	  Entreprise ET = entrepriseRepository.save(new
	  Entreprise("New Dev","Devellopement" , "Fes" , new Date(), "0928292",CE));
	  
	  Offre O = new Offre(ET,"Dev",new
	  Date(),"des","miss","comp","durr","fes","sujet");
	  
	  offreRepository.save(O);
	  
	  O.getEtudiants().add(E);
	  
	  offreRepository.save(O);
	  
	  // for(Etudiant E1 : O.getEtudiants()) { //
	  System.out.println(E1.getNom_etd()); // }
	  
	  
	  Convention c = ecoleMetierImpl.GenereConvention(E.getId_etd(), O.getId_off(),
	  ecole.getId_eco()); Collection<Etudiant> listeE =
	  entrepriseMetierImpl.ConsulterListePostule(O.getId_off());
	  
	  for(Etudiant ET1 : listeE) { System.out.println(ET1.getNom_etd()); }
	  
	  entrepriseMetierImpl.SupprimerOffre(O.getId_off());
	  
	  Compte C = comptemetier.login("AdminEcole@gmail.com","1111"); if(C instanceof
	  CompteAdminEcole) { System.out.println("le compte est vide"); }else {
	  System.out.println(C.getMail_comp()+C.getModt_comp()); }
	  
	  */
		  
//		 System.out.println(ecoleMetierImpl.GenererPasse());
//		 CompteEntrepriseInterne C = compteRepository.CompteEntrepriseInerne(3);
		  
		// CompteEntrepriseInterne C= entrepriseMetierImpl.CreeCompteIntern(4, "zaki@hhhh", "1111");
		 
//		 Collection<CompteEntrepriseInterne> CEI= entrepriseMetierImpl.ListeCompteInterne(4);
//		for(CompteEntrepriseInterne CE : CEI) {
//			System.out.println(CE.getMail_comp());
//		}

		
//		  Collection<Offre> cl = new ArrayList<Offre>(); 
//		  cl = etudiantMetier.ListerOffresEtudiant("Security");
//		  
//		  for(Offre o : cl) { System.out.println(o.getDom_off()+o.getDat_off()); }
//		 
		  //etudiantMetier.postuleOffre(7,9);
		  
			/*
			 * CV cv = cvRepository.getCVEtudiant(9); System.out.println(cv.getGithub());
			 * 
			 * Collection<Experiences> experiences = etudiantMetier.ListExperience(9);
			 * for(Experiences F : experiences) { System.out.println(F.getDesc_exp()); }
			 * 
			 * Collection<Etudiant> E = entrepriseMetierImpl.ListEtudiantPostuler(7);
			 * for(Etudiant e : E) { System.out.println(e.getNom_etd()); } //
			 *///		  CV cv1 = entrepriseMetierImpl.CVEtudiant(9);
//		  for(Experiences E2 : cv1.getExperiences()) {
//			  System.out.println(E2.getDesc_exp());
//		  }
//		  //System.out.println(cv1.getGithub());
//		  Collection<Experiences> ex = entrepriseMetierImpl.CVEtudiantExperience(9);
//		  Collection<Formation> fr = entrepriseMetierImpl.CVEtudiantFormation(9);
//		  Collection<ProjetsReal> pr = entrepriseMetierImpl.CVEtudiantProjet(9);
//		  Collection<Hobbies> hb = entrepriseMetierImpl .CVEtudiantHobbies(9);
//		  for(Experiences e : ex ) {
//			  System.out.println(e.getDesc_exp());
//		  }
//		  for(Formation e : fr ) {
//			  System.out.println(e.getFormation());
//		  }
//		  for(ProjetsReal e : pr ) {
//			  System.out.println(e.getDesc_proj());
//		  }
//		  for(Hobbies e : hb ) {
//			  System.out.println(e.getHobbie());
//		  }
//		  
//		  
//		  Collection<Entretien> ent = etudiantMetier.ListeEntretien(9);
//		  for(Entretien e : ent ) {
//			  System.out.println(e.getDate_entr());
//			  System.out.println(e.getOffre().getSujet_off());
//		  }
		  
		  
		  //responsableRepository.save(new Responsable("Responsable","111"));
		  
		
		  
		
		 
		 //responsableRepository.save(new Responsable("Responsable@gmail.com", "1111","Zohair", "Moufakkir", "CD5261","0677990088"));
	  }
	 

}
