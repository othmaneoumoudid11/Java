package com.example.demo.dao;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Entreprise;
import com.example.demo.entities.Entretien;

@Transactional
public interface EntretienRepository extends JpaRepository<Entretien, Integer> {
	@Query("select  E from Entretien E where E.etudiant.id_etd =:x  and E.ref_entr=2 ") 
	Collection<Entretien> ListeEntretien(@Param("x") Integer id_etd );
	
	@Modifying
	@Query("update Entretien E set E.ref_entr=1 where E.id_ent = ?1") 
	void AcceptEntretien(Integer id_etd );
	
	
	@Modifying
	@Query("update Entretien E set E.ref_entr=0 where E.id_ent = ?1") 
	void RefuserEntretien(Integer id_ent );
	
	@Modifying
	 @Query("delete from Entretien E where E.etudiant.id_etd =?1") 
	 void Supprimer_entretiens_etudiant(Integer id_etd );
	

	@Query("select  E from Entretien E where E.etudiant.id_etd =:x and E.ref_entr=1 and E.val_cand=4") 
	Collection<Entretien> ListeEntretienValider(@Param("x") Integer id_etd );
	
	@Query("select  E from Entretien E where E.entreprise.id_entr =:x and E.val_cand=1") 
	Collection<Entretien> ListeStagiaire(@Param("x") Integer id_entrepise );
	
	
	@Modifying
	@Query("update Entretien E set E.val_cand=1 where E.id_ent = ?1") 
	void validerCondidature(Integer id_e );
	
	@Modifying
	@Query("update Entretien E set E.val_cand=4 where E.id_ent = ?1") 
	void ValiderConvention(Integer id_e );
	
	
	@Modifying
	@Query("update Entretien E set E.val_cand=0 where E.id_ent = ?1") 
	void acc0(Integer id_e );
	@Modifying
	@Query("update Entretien E set E.val_cand=1 where E.etudiant.id_etd= ?1 and E.offre.id_off = ?2") 
	void valid_cand(Integer id_e, Integer id_off );
	
	@Modifying
	@Query("update Entretien E set E.val_cand=0 where E.etudiant.id_etd= ?1 and E.offre.id_off = ?2") 
	void Non_valid_cand(Integer id_e, Integer id_off );

	@Query("select  E from Entretien E where E.entreprise.id_entr =:x and E.ref_entr=1 and (E.val_cand=2 or E.val_cand=4) ") 
	Collection<Entretien> ListeEntretienValiderEtudiant(@Param("x") Integer id_entrepise );
	
	
	@Modifying
	@Query("update Entretien E set E.val_cand=3 where E.etudiant.id_etd= ?1 and E.offre.id_off = ?2") 
	void archiverEtudiant(Integer id_e,Integer id_of);
	
	

	@Query("select  count(*) from Entretien E where E.entreprise.id_entr =:x and (E.ref_entr=1 or E.ref_entr=3)  ") 
	int nbr_entretiens(@Param("x") Integer id_entrepise );
	
	
	@Query("select  count(*) from Entretien E where E.entreprise.id_entr = ?1 and (E.ref_entr=1 or E.ref_entr=3) and E.date_entr  like CONCAT('%',?2,'-',?3,'%') ") 
	int nbr_entretiens_month(Integer id_entrepise, String month , Integer year );
	

	@Query("select  count(*) from Entretien E where E.etudiant.id_etd = ?1 and E.ref_entr=3 and E.date_entr like CONCAT('%','-',?2,'%') and E.val_cand=3 ") 
	int nbr_stages_an(Integer id_etd, Integer year );
	
	
	@Query("select  count(*) from Entretien E where E.etudiant.id_etd = ?1 and (E.ref_entr=3 or E.ref_entr=1 ) and E.date_entr like CONCAT('%','-',?2,'%')  ") 
	int nbr_entretiens_an(Integer id_etd, Integer year );
	
	
	@Modifying
	@Query("update Entretien E set E.ref_entr=3 where E.etudiant.id_etd= ?1 and E.offre.id_off = ?2") 
	void ConventionAttribuer(Integer id_e,Integer id_of);
	
	@Query("select  count(*) from Entretien E where E.etudiant.id_etd = ?1 ") 
	int nbr_entretiens_Etudiant(Integer id_etd);
	
	
}
