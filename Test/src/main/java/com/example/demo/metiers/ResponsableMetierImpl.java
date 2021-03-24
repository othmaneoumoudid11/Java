package com.example.demo.metiers;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.CompteRepository;
import com.example.demo.dao.EcoleRepository;
import com.example.demo.dao.EntrepriseRepository;
import com.example.demo.dao.ResponsableRepository;
import com.example.demo.entities.Compte;
import com.example.demo.entities.CompteAdminEcole;
import com.example.demo.entities.CompteEntreprise;
import com.example.demo.entities.Ecole;
import com.example.demo.entities.Entreprise;
import com.example.demo.entities.Responsable;

@Service
@Transactional
public class ResponsableMetierImpl implements IResponsableMetier {

	@Autowired
	ResponsableRepository responsableRepository;
	
	@Autowired
	CompteRepository compteRepository;
	
	
	@Autowired
	EntrepriseRepository entrepriseRepository;
	
	@Autowired
	EcoleRepository ecoleRepository;
	
	@Override
	public Collection<Ecole> ListeEcole(int id_res) {
		return ecoleRepository.ListeEcoleResponsable(id_res);
	}

	@Override
	public Collection<Entreprise> ListeEntreprise(int id_res) {
		return entrepriseRepository.ListeEntrepriseResponsable(id_res);
	}

	@Override
	public void AjouterEcole(Compte compte, String nom_ecole, String ville_eco, String mail_eco, String tele_eco,String addr_eco,Responsable R) {
		ecoleRepository.save(new Ecole(compte, nom_ecole, ville_eco, mail_eco, tele_eco, addr_eco,R));
	}

	@Override
	public void AjouterEntreprise(String nom_entr, String dom_entr, String addr_entr, LocalDate date_entr,String tele_fix_entr, Compte compte,Responsable R) {
		    entrepriseRepository.save(new Entreprise( nom_entr, dom_entr, addr_entr, date_entr,tele_fix_entr, compte,R));
	}

	@Override
	public Responsable LoginResponsable(String email, String MDP) {
		Responsable R =responsableRepository.VerifierCompte(email,MDP);
		return R;
	}

	@Override
	public Compte CompteEntreprise(String mail, String MDP) {
		return compteRepository.save(new CompteEntreprise( mail, MDP , true));
	}

	@Override
	public Compte CompteEcole(String mail, String MDP) {
		return compteRepository.save(new CompteAdminEcole( mail, MDP , true));
	}

	@Override
	public void SupprimerCompteEcole(Ecole E) {
		ecoleRepository.SupprimerEcole(E.getId_eco());
		compteRepository.Supprimer_compte_etudiant(E.getCompte().getId_comp());
	}

	@Override
	public void SupprimerCompteEntreprise(int ID_ENT) {
		entrepriseRepository.deleteById(ID_ENT);
		
	}

	@Override
	public void ActiverCompteEcole(int ID_CE) {
		Ecole E = ecoleRepository.findById(ID_CE).orElse(null);
		compteRepository.ActiverCompteEcole(E.getCompte().getId_comp());
	}

	@Override
	public void DesactiverCompteEcole(int ID_CE) {
		Ecole E = ecoleRepository.findById(ID_CE).orElse(null);
		compteRepository.DesactiverCompteEcole(E.getCompte().getId_comp());
	}

	@Override
	public void ActiverCompteEntreprise(int ID_ETR) {
		Entreprise E = entrepriseRepository.findById(ID_ETR).orElse(null);
		compteRepository.ActiverCompteEntreprise(E.getCompte().getId_comp());
	}

	@Override
	public void DesactiverCompteEntreprise(int ID_ETR) {
		Entreprise E = entrepriseRepository.findById(ID_ETR).orElse(null);
		compteRepository.DesactiverCompteEntreprise(E.getCompte().getId_comp());
		
	}

	@Override
	public int NbreEcole(int id_res) {
		return ecoleRepository.NbreEcole(id_res);
	}

	@Override
	public int NbreEntreprise(int id_res) {
		return entrepriseRepository.NbreEntreprise(id_res);
	}

	

	
	
}
