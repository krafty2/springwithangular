package com.spring.angular.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.angular.models.Demande;
import com.spring.angular.service.ClientService;
import com.spring.angular.service.DemandeService;
import com.spring.angular.service.OffreService;

@RestController
@RequestMapping("/demande/")
@CrossOrigin(origins = "*")
public class DemandeController {

	private DemandeService demandeService;
	public DemandeController(DemandeService demandeService, ClientService clientService, OffreService offreService) {
		super();
		this.demandeService = demandeService;
	}
	
	@GetMapping("/recrutement")
	public List<Demande> allRecrut(){
		return demandeService.typeDemandes("recrutement");
	}
	
	@GetMapping("/reabonnement")
	public List<Demande> allReabo(){
		return demandeService.typeDemandes("reabonnement");
	}
	
}
