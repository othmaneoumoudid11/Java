package com.example.demo.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Convention;
import com.example.demo.entities.Ecole;
import com.example.demo.entities.Entretien;
import com.example.demo.entities.Etudiant;

@Transactional
public interface ConventionRepository extends JpaRepository<Convention, Integer> {
	
	
	
	@Query("select  C from Convention C where C.ecole.id_eco =:x and C.accept=0 ") 
	Collection<Convention> ConventionsEcole(@Param("x") Integer id_eco );
	
	@Query("select  C from Convention C where C.etudiant.id_etd =:x and C.accept = 1 ") 
	Collection<Convention> ConventionsEtudiant(@Param("x") Integer id_etd );
	 
	 
	 @Modifying
	 @Query("delete from Convention C where C.offre.id_off =:x") 
	 void Supprimer(@Param("x") Integer id_off );
	 
	 
	 @Modifying
	 @Query("delete from Convention C where C.etudiant.id_etd =:x") 
	 void Supprimer_conventions_etudiant(@Param("x") Integer id_etd );
	 
	 
	 
	 @Modifying
	 @Query("update Convention C set C.accept = 1 where C.id_conv =:x") 
	 void AccepterSujet(@Param("x") Integer id_off );
	 
	 
	 
	 @Modifying
	 @Query("update Convention C set C.accept = 2 where C.id_conv =:x") 
	 void acc0(@Param("x") Integer id_cnv );
	 
	 
	 @Query("select  C from Convention C where C.etudiant.id_etd =:x and C.offre.id_off =:y ") 
	 Convention ConventionEntreprise(@Param("x") Integer id_etd,@Param("y") Integer id_off  );
		 
	 
	 
	 @Query("select count(*) from Convention C where C.etudiant.id_etd =:x and C.offre.id_off =:y ") 
	 Integer  verifierConvention(@Param("x") Integer id_etd,@Param("y") Integer id_off  );
	
//	 @Query("insert into Convention (ID_ECO,ID_ETD,ID_OFF) values(?,?,?)") 
//	 void DemandeConvention( Integer id_eco , Integer id_etd, Integer id_off  );
	 
	 
	 @Query("select count(*) from Convention C where C.etudiant.id_etd =:x") 
	 Integer  nbreConvention(@Param("x") Integer id_etd);
	 
	 
}
