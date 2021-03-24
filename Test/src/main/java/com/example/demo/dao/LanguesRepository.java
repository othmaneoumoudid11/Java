package com.example.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Langues;
import com.example.demo.entities.ProjetsReal;

@Transactional
public interface LanguesRepository extends JpaRepository<Langues, Integer> {

	@Query("select L from Langues L where L.cv.Id_cv =?1") 
	 List<Langues>  ListeLangues(Integer id_cv);
	

	@Query("select  count(*) from Langues L where L.cv.etudiant.id_etd = ?1") 
	int nbreLangue(Integer id_etd);
	
	@Modifying
	 @Query("delete from Langues L where L.cv.Id_cv =?1") 
	 void Supprimer_lang_etudiant(Integer id_cv );
	
	
}
