package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.entities.Responsable;

public interface ResponsableRepository extends JpaRepository<Responsable, Integer>  {
	@Query("select  R  from Responsable R  where R.mail_res = ?1 and R.modt_res = ?2") 
	Responsable VerifierCompte(String email, String mdp);
}
