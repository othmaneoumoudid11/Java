package com.example.demo.metiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CompteRepository;
import com.example.demo.entities.Compte;

@Service
public class CompteMetierImpl implements ICompte{
   
	@Autowired
	CompteRepository compteRepository;
	@Override
	public Compte login(String email,String MDP) {
		Compte C =compteRepository.VerifierCompte(email,MDP);
		if(C==null) {System.out.println("compte ");return null;}
		else return C;
	}


}
