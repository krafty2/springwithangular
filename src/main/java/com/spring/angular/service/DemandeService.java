package com.spring.angular.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.angular.enums.Status;
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
	
//	public List<Object[]> listRecrutement(){
//		return demandeRepository.listeRecrutDemande("recrutement");
//	}
	
	public List<Demande> typeDemandes(String typeDemande){
		return demandeRepository.findByTypeDemande(typeDemande);
	}
	
	public List<Demande> reabonnementAttente(Status status,String typeDemande){
		return demandeRepository.findByStatusAndTypeDemande(status,typeDemande);
	}
}
