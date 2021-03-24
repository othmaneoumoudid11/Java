package com.example.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Hobbies;
import com.example.demo.entities.ProjetsReal;

@Transactional
public interface HobbiesRepository extends JpaRepository<Hobbies, Integer> {

	@Query("select H from Hobbies H where H.cv.Id_cv =?1") 
	 List<Hobbies>  ListeHobbies(Integer id_cv);
	
	@Modifying
	 @Query("delete from Hobbies H where H.cv.Id_cv =?1") 
	 void Supprimer_hobi_etudiant(Integer id_cv );
	
}
