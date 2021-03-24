package com.example.demo.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Ecole;
import com.example.demo.entities.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {
	@Query("select  E from Entreprise E where E.compte.id_comp =:x") 
	Entreprise EntrepriseCompte(@Param("x") Integer id_comp );
	
	@Query("select count(*) from Entreprise E") 
    int countEntreprise();
	
	@Query("select  E from Entreprise E where E.responsable.id_res =:x") 
	Collection<Entreprise> ListeEntrepriseResponsable(@Param("x") Integer id_res );
	
	@Query("select  count(*) from Entreprise E where E.responsable.id_res =:x") 
	Integer NbreEntreprise(@Param("x") Integer id_res );
	
}
