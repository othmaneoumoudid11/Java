package com.example.demo.dao;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.demo.entities.Filiere;

@Transactional
public interface FiliereRepository extends JpaRepository<Filiere, Integer> {
	
	@Query("select  F from Filiere F where F.ecole.id_eco =:x") 
	Collection<Filiere> ListeFiliere(@Param("x") Integer id_ecole);
	
	
	@Modifying
	@Query("update Filiere F set F.filiere =?1 ,F.abreviation =?2 where F.id_filiere = ?3") 
	void Modifier_filiere(String filiere,String Abreviation ,Integer id_filiere);

	@Modifying
	@Query("delete from Filiere F where F.ecole.id_eco =:x") 
	void SupprimerFilieres(@Param("x") Integer id_ecole);
}
