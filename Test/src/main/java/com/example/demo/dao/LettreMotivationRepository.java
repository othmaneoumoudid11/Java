package com.example.demo.dao;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.LettreMotivation;

@Transactional
public interface LettreMotivationRepository extends JpaRepository<LettreMotivation, Integer> {

	 @Query("Select L from LettreMotivation L where L.etudiant.id_etd = ?1 and L.offre.id_off= ?2") 
	 LettreMotivation getLettre(Integer id_etd, Integer id_off);
	 
	 
	 @Query("Select count(*) from LettreMotivation L where L.etudiant.id_etd = ?1 and L.offre.id_off= ?2") 
	 Integer  verifierLettre(Integer id_etd, Integer id_off);
	
	 @Modifying
	 @Query("delete from LettreMotivation L where L.etudiant.id_etd =?1") 
	 void Supprimer_lettre_etudiant(Integer id_etd );
	
	 
	 @Query("Select count(*) from LettreMotivation L where L.etudiant.id_etd = ?1") 
	 Integer  nbreLettreMotivation(Integer id_etd);
	
	 
}
