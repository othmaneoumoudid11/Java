package com.example.demo.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Competences;

@Transactional
public interface CompetencesRepository extends JpaRepository<Competences, Integer> {

	 @Modifying
	 @Query("delete from Competences C where C.cv.Id_cv =?1") 
	 void Supprimer_comp_etudiant(Integer id_cv );
}
