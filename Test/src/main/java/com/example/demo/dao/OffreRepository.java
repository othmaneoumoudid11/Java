package com.example.demo.dao;


import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Etudiant;
import com.example.demo.entities.Offre;

public interface OffreRepository extends JpaRepository<Offre, Integer>{
	 @Query("select  O from Offre O where O.entreprise.id_entr=:id") 
	 Collection<Offre> ListOffres(@Param("id") Integer id);
	 
	@Query("select  O.etudiants from Offre O  where O.id_off = :id") 
	Collection<Etudiant> ListEtudiantPostule(@Param("id") Integer id);
	
	@Query("select  O from Offre O where O.dom_off = :dom") 
	 Collection<Offre> ListOffresEtudiat(@Param("dom") String domaine);
	
	@Query("select  count(*) from Offre O where O.entreprise.id_entr = :id_entr") 
	 int nbr_offres(@Param("id_entr") Integer id_entr);
	
	@Query("select  count(*) from Offre O where O.entreprise.id_entr = ?1 and O.dat_off like CONCAT('%',?2,'-',?3,'%')") 
	 int nbr_offres_month(Integer id_entr, String month , Integer year);
	
}
