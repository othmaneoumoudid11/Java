package com.example.demo.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Compte;
import com.example.demo.entities.CompteEntrepriseInterne;


public interface CompteRepository extends JpaRepository<Compte, Integer> {
	@Query("select  C  from Compte C  where C.mail_comp = ?1 and C.modt_comp = ?2") 
	Compte VerifierCompte(String email, String mdp);
    
	@Modifying
	@Query("update Compte C set C.active = 0 where C.id_comp =?1") 
	void DesactiverCompteEcole(Integer id_eco);
	
	@Modifying
	@Query("update Compte C set C.active = 1 where C.id_comp =?1") 
	void ActiverCompteEcole(Integer id_eco);
	
	
	
	@Modifying
	@Query("update Compte C set C.active = 0 where C.id_comp =?1") 
	void DesactiverCompteEntreprise(Integer id_ent);
	
	@Modifying
	@Query("update Compte C set C.active = 1 where C.id_comp =?1") 
	void ActiverCompteEntreprise(Integer id_ent);
	
	
	@Query("select  C  from Compte C ") 
	Collection<Compte> nbre_intern_compte();
	
	@Modifying
	 @Query("delete from Compte C where C.id_comp =?1") 
	 void Supprimer_compte_etudiant(Integer id_comp );
	
	
	
}
