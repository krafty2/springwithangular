package com.spring.angular.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.angular.models.Utilisateur;
import com.spring.angular.repository.UtilisateurRepository;

@Service
public class UtilisateurService {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	public Optional<Utilisateur> searchByNom(String nom){
		return utilisateurRepository.findByNom(nom);
	}
}
