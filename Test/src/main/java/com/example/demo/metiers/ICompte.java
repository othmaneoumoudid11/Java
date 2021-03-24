package com.example.demo.metiers;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Compte;


public interface ICompte {
   public Compte login(String email ,String MDP);
}
