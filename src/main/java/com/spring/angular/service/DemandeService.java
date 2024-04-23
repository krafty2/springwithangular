package com.spring.angular.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.angular.models.Demande;
import com.spring.angular.repository.DemandeRepository;

@Service
public class DemandeService {

	private DemandeRepository demandeRepository;

	public DemandeService(DemandeRepository demandeRepository) {
		super();
		this.demandeRepository = demandeRepository;
	}
	
	public Demande saveDemande(Demande demande) {
		return demandeRepository.save(demande);
	}
	
	public List<Demande> listDemande(){
		return demandeRepository.findAll();
	}
	
	public List<Demande> listRecrutement(){
		return demandeRepository.listeRecrutDemande();
	}
}
