package com.example.demo.metiers;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

import com.example.demo.entities.*;

public interface IResponsableMetier {
	
	public Collection<Ecole> ListeEcole(int id_res);
	public Collection<Entreprise> ListeEntreprise(int id_res);
	public void AjouterEcole(Compte compte, String nom_ecole, String ville_eco, String mail_eco, String tele_eco, String addr_eco,Responsable R);
	public void AjouterEntreprise(String nom_entr, String dom_entr, String addr_entr, LocalDate date_entr, String tele_fix_entr,Compte compte,Responsable R);
    public Responsable LoginResponsable(String email,String MDP);
    public Compte CompteEntreprise(String mail,String MDP);
    public Compte CompteEcole(String mail,String MDP);
    public void SupprimerCompteEcole(Ecole E);
    public void ActiverCompteEcole(int ID_CE);
    public void DesactiverCompteEcole(int ID_CE);
    public void SupprimerCompteEntreprise(int ID_ETR);
    public void ActiverCompteEntreprise(int ID_ETR);
    public void DesactiverCompteEntreprise(int ID_ETR);
    public int NbreEcole(int id_res);
    public int NbreEntreprise(int id_res);
    
}
