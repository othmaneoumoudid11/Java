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
import com.example.demo.entities.Filiere;



@Transactional
public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {
	
 @Query("select  E from Etudiant E where E.ecole.id_eco =:x and  E.stage_tr=0") 
 List<Etudiant> ListEtudiantSansStage(@Param("x") Integer id_ecole );

 @Query("select E from Etudiant E where E.compte.id_comp IS NULL and E.ecole.id_eco=?1") 
 List<Etudiant>  EtudiantSansCompte(Integer id_ecole);
 
 
 @Modifying
 @Query("update Etudiant E set E.nom_etd =?1, E.prenom_etd=?2, E.mail_etd =?3, E.tele_etd=?4, E.filiere=?5  ,E.dom_etd=?6 where E.id_etd=?7") 
 void  ModifierEtudiant(String first_name, String last_name, String email,String phone, Filiere filiere, String domaine,Integer id_etd);


 @Query("select  E from Etudiant E where E.compte.id_comp =:x") 
 Etudiant EtudiantCompte(@Param("x") Integer id_comp );
 
 @Query("select  E from Etudiant E where E.id_etd =?1") 
 Etudiant select_Etudiant(Integer id_etd );

 @Query("select  count(*) from Etudiant E where E.ecole.id_eco =:x") 
	int  countetudiant(@Param("x") Integer id_eco );
 
 
 @Query("select  count(*) from Etudiant E where E.stage_tr =0 and E.ecole.id_eco =:x") 
int  CountEtudiantSansStage(@Param("x") Integer id_eco );
 

 @Query("select  count(*) from Etudiant E where E.stage_tr = 1 and E.ecole.id_eco =:x") 
int  countEtudiantStagiaire(@Param("x") Integer id_eco );
 
@Modifying
@Query("update Etudiant E set E.stage_tr=1 where E.id_etd = ?1 ") 
void Stage_Trouver(Integer id_etd);


@Modifying
@Query("update Etudiant E set E.stage_tr=0 where E.id_etd = ?1 ") 
void Stage_Fini(Integer id_etd);


@Modifying
@Query("update Etudiant E set E.stage_tr=0 where E.id_etd = ?1 ") 
void NonStage(Integer id_etd);


@Modifying
@Query("update Etudiant E set E.stage_tr=1 where E.id_etd = ?1 ") 
void StageTrouve(Integer id_etd);
 

@Modifying
@Query("delete from  Etudiant E  where E.id_etd = ?1 ") 
void SupprimerEtudiant(Integer id_etd);

}
