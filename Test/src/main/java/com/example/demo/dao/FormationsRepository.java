package com.example.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Experiences;
import com.example.demo.entities.Formation;

@Transactional
public interface FormationsRepository extends JpaRepository<Formation, Integer> {
	@Query("select F from Formation F where F.cv.Id_cv =?1 order by F.date_fin desc") 
	 List<Formation>  ListeFormations(Integer id_cv);
	
	
	@Query("select  count(*) from Formation F where F.cv.etudiant.id_etd=?1") 
	int nbreDiplome(Integer id_etd);
	
	@Modifying
	 @Query("delete from Formation F where F.cv.Id_cv =?1") 
	 void Supprimer_Form_etudiant(Integer id_cv );
	
}
