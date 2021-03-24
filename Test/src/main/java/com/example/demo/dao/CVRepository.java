package com.example.demo.dao;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.CV;

@Transactional
public interface CVRepository extends JpaRepository<CV, Integer> {

	@Query("select C from CV C where C.etudiant.id_etd=?1")
	CV getCVEtudiant(Integer id_etd);
	
	
	@Modifying
	@Query("update CV C set C.linkedin=?1 , C.Github=?2,  C.Info_cv=?3  where C.etudiant.id_etd = ?4 ") 
	public void UpdateCV( String linkdin, String ghithub, String info ,int id_etd);
	
	
	@Modifying
	 @Query("delete from CV C where C.Id_cv =?1") 
	 void Supprimer_cv_etudiant(Integer id_cv );
}
