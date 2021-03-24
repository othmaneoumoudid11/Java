package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Etudiant;
import com.example.demo.entities.Experiences;

public interface ExperiencesRepository extends JpaRepository<Experiences, Integer> {
	@Query("select E from Experiences E where E.cv.Id_cv =?1 order by E.date_debut desc") 
	 List<Experiences>  ListeExperiences(Integer id_cv);
	
	@Modifying
	 @Query("delete from Experiences E where E.cv.Id_cv =?1") 
	 void Supprimer_exp_etudiant(Integer id_cv );
}

