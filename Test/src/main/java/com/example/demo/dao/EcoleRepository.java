package com.example.demo.dao;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Ecole;
import com.example.demo.entities.Etudiant;

@Transactional
public interface EcoleRepository extends JpaRepository<Ecole, Integer> {

	@Query("select  E from Ecole E where E.compte.id_comp =:x") 
	Ecole EcoleCompte(@Param("x") Integer id_comp );
	
	@Query("select  E from Ecole E where E.responsable.id_res =:x") 
	Collection<Ecole> ListeEcoleResponsable(@Param("x") Integer id_res);
	
	

	@Query("select  count(*) from Ecole E where E.responsable.id_res =:x") 
	Integer NbreEcole(@Param("x") Integer id_res);
	
	
    @Modifying
	@Query("delete from  Ecole E where E.id_eco =:x") 
	Integer SupprimerEcole(@Param("x") Integer id_res);
}
