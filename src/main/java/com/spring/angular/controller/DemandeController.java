package com.spring.angular.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.angular.enums.Status;
import com.spring.angular.models.Client;
import com.spring.angular.models.Demande;
import com.spring.angular.models.Offre;
import com.spring.angular.records.ReabonnementRecord;
import com.spring.angular.records.RecrutementRecord;
import com.spring.angular.service.ClientService;
import com.spring.angular.service.DemandeService;
import com.spring.angular.service.OffreService;

@RestController
@RequestMapping("/demande/")
@CrossOrigin(origins = "*")
public class DemandeController {

	private DemandeService demandeService;
	private OffreService offreService;
	private ClientService clientService;
	
	public DemandeController(DemandeService demandeService, OffreService offreService, ClientService clientService) {
		super();
		this.demandeService = demandeService;
		this.offreService = offreService;
		this.clientService = clientService;
	}

	// Get api
	
	@GetMapping("/recrutement")
	public List<Demande> allRecrut(){
		return demandeService.typeDemandes("recrutement");
	}
	
	@GetMapping("/reabonnement")
	public List<Demande> allReabo(){
		return demandeService.typeDemandes("reabonnement");
	}
	
	@GetMapping("/reabo_en_attente")
	public List<Demande> reaboEnAttente(){
		return demandeService.reabonnementAttente(Status.EN_ATTENTE,"reabonnement");
	}
	
	@GetMapping("/recru_en_attente")
	public List<Demande> recruEnAttente(){
		return demandeService.reabonnementAttente(Status.EN_ATTENTE,"recrutement");
	}
	
	
	//Post api
	
	@PostMapping("/save_demande")
	public ResponseEntity<ReabonnementRecord> saveReabo(@RequestBody ReabonnementRecord object){
		
		if(object!=null) {
			
			Demande demande = object.demande();
			
			Optional<Offre> existingOffre = offreService.searchOffreByBouquet(object.bouquet());
			
			Optional<Client> existingClient = clientService.searchClientByDecodeur(object.numero_decodeur());
			
			if(existingClient.isPresent()) {
				if(existingOffre.isPresent()) {
					demande.setOffre(existingOffre.get());
					demande.setClient(existingClient.get());
					demandeService.saveDemande(demande);
				}
			} else {
				
				if(existingOffre.isPresent()) {
					Client client = new Client();
					client.setDecodeur(object.numero_decodeur());		
					clientService.saveClient(client);
					
					demande.setClient(client);
					demande.setOffre(existingOffre.get());
					demandeService.saveDemande(demande);
				}
			}
			
			return new ResponseEntity<>(object,HttpStatus.CREATED);
		}
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/recrutement_without_parabole")
	public ResponseEntity<RecrutementRecord> saveRecru(@RequestBody RecrutementRecord object){
		
		if(object!=null) {
			
			Demande demande = object.demande();
			
			Optional<Offre> existingOffre = offreService.searchOffreByBouquet(object.bouquet());
			
			Optional<Client> existingClient = clientService.searchClientByDecodeur(object.client().getDecodeur());
			
			if(existingClient.isPresent()) {
				if(existingOffre.isPresent()) {
					demande.setOffre(existingOffre.get());
					demande.setClient(existingClient.get());
					demandeService.saveDemande(demande);
				}
			} else {
				
				if(existingOffre.isPresent()) {
					Client client = new Client();
					client.setDecodeur(object.client().getDecodeur());		
					clientService.saveClient(client);
					
					demande.setClient(client);
					demande.setOffre(existingOffre.get());
					demandeService.saveDemande(demande);
				}
			}
			
			return new ResponseEntity<>(object,HttpStatus.CREATED);
		}
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/recrutement_with_parabole")
	public ResponseEntity<RecrutementRecord> saveRecruWithPara(@RequestBody RecrutementRecord object){
		
		if(object!=null) {
			
			Demande demande = object.demande();
			
			Optional<Offre> existingOffre = offreService.searchOffreByBouquet(object.bouquet());
			
			Optional<Client> existingClient = clientService.searchClientByDecodeur(object.client().getDecodeur());
			
			if(existingClient.isPresent()) {
				if(existingOffre.isPresent()) {
					demande.setOffre(existingOffre.get());
					demande.setClient(existingClient.get());
					demandeService.saveDemande(demande);
				}
			} else {
				
				if(existingOffre.isPresent()) {
					Client client = new Client();
					client.setDecodeur(object.client().getDecodeur());		
					clientService.saveClient(client);
					
					demande.setClient(client);
					demande.setOffre(existingOffre.get());
					demandeService.saveDemande(demande);
				}
			}
			
			return new ResponseEntity<>(object,HttpStatus.CREATED);
		}
		
		return ResponseEntity.ok().build();
	}
	
}
