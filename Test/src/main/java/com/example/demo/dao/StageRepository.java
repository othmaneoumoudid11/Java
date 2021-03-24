package com.example.demo.dao;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.entities.Stage;

@Transactional
public interface StageRepository extends JpaRepository<Stage, Integer> {
	@Query("select  S from Stage S where S.etudiant.id_etd = ?1") 
	Collection<Stage> ListeStage(Integer id_etd );
	
	
	@Query("select  count(*) from Stage S where S.etudiant.id_etd = ?1") 
	int nbreStage(Integer id_etd);
	
	@Modifying
	@Query("update Stage S set S.note_stage =?1 where S.etudiant.id_etd = ?2 and S.offre.id_off=?3") 
	void NoteStage(Double not,Integer id_etd, Integer id_off);
	
	 @Modifying
	 @Query("delete from Stage S where S.id_stage =?1") 
	 void Supprimer_stages_etudiant(Integer id_stage );
	
	@Query("select  count(*) from Stage S where S.entreprise.id_entr = ?1") 
	int nbr_stagaires(Integer id_entr);
	
	
	
	
}
