package com.example.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Experiences;
import com.example.demo.entities.ProjetsReal;



@Transactional

public interface ProjetsRealRepository extends JpaRepository<ProjetsReal, Integer> {
	@Query("select P from ProjetsReal P where P.cv.Id_cv =?1 order by P.date_proj desc") 
	 List<ProjetsReal>  ListeProjet(Integer id_cv);
	
	@Query("select  count(*) from ProjetsReal P where P.cv.etudiant.id_etd=?1") 
	int nbreProjet(Integer id_etd);
	
	 @Modifying
	 @Query("delete from ProjetsReal P where P.cv.Id_cv =?1") 
	 void Supprimer_projr_etudiant(Integer id_cv );
	
}
