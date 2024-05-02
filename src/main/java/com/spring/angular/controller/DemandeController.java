package com.spring.angular.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.angular.enums.Status;
import com.spring.angular.models.Client;
import com.spring.angular.models.Demande;
import com.spring.angular.models.Distributeur;
import com.spring.angular.models.Offre;
import com.spring.angular.models.Utilisateur;
import com.spring.angular.records.ReabonnementRecord;
import com.spring.angular.records.RecrutementRecord;
import com.spring.angular.service.ClientService;
import com.spring.angular.service.DemandeService;
import com.spring.angular.service.DistributeurService;
import com.spring.angular.service.OffreService;
import com.spring.angular.service.UtilisateurService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/demande/")
@CrossOrigin(origins = "*")
public class DemandeController {

	@Autowired
	private DemandeService demandeService;
	@Autowired
	private OffreService offreService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private UtilisateurService utilisateurService;
	
	// Get api
	
	@GetMapping("/recrutement")
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasAuthority('SCOPE_Gerant')")
	public List<Demande> allRecrut(Authentication authentication){
		System.out.println(authentication.getName());
		return demandeService.typeDemandes("recrutement");
	}
	
	
	@GetMapping("/reabonnement")
	@PreAuthorize("hasAuthority('SCOPE_Gerant')")
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
	public ResponseEntity<ReabonnementRecord> saveReabo(@RequestBody ReabonnementRecord object,Authentication authentication){
		
		if(object!=null) {
			
			Demande demande = object.demande();
			
			Optional<Offre> existingOffre = offreService.searchOffreByBouquet(object.bouquet());
			
			Optional<Client> existingClient = clientService.searchClientByDecodeur(object.numero_decodeur());
			
			Optional<Utilisateur> existingDistributeur =  utilisateurService.searchByUserName(authentication.getName()); ;
			System.out.println(existingDistributeur.get());
			if(existingClient.isPresent()) {
				if(existingOffre.isPresent()) {
					demande.setOffre(existingOffre.get());
					demande.setClient(existingClient.get());
					demande.setDistributeur((Distributeur) existingDistributeur.get());
					demandeService.saveDemande(demande);
				}
			} else {
				
				if(existingOffre.isPresent()) {
					Client client = new Client();
					client.setDecodeur(object.numero_decodeur());		
					clientService.saveClient(client);
					
					demande.setClient(client);
					demande.setOffre(existingOffre.get());
					demande.setDistributeur((Distributeur) existingDistributeur.get());
					demandeService.saveDemande(demande);
				}
			}
			
			return new ResponseEntity<>(object,HttpStatus.CREATED);
		}
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/recrutement")
	public ResponseEntity<RecrutementRecord> saveRecru(@RequestBody RecrutementRecord object,Authentication authentication){
		
		if(object!=null) {
			
			Demande demande = object.demande();
			
			Optional<Offre> existingOffre = offreService.searchOffreByBouquet(object.bouquet());
			
			Optional<Client> existingClient = clientService.searchClientByDecodeur(object.client().getDecodeur());
			
			Optional<Utilisateur> existingDistributeur =  utilisateurService.searchByUserName(authentication.getName()); ;
			
			if(existingClient.isPresent()) {
				if(existingOffre.isPresent()) {
					demande.setOffre(existingOffre.get());
					demande.setClient(existingClient.get());
					demande.setDistributeur((Distributeur) existingDistributeur.get());
					demandeService.saveDemande(demande);
				}
			} else {
				
				if(existingOffre.isPresent()) {
					Client client = object.client();
					//client.setDecodeur(object.client().getDecodeur());		
					clientService.saveClient(client);
					
					demande.setClient(client);
					demande.setOffre(existingOffre.get());
					demande.setDistributeur((Distributeur) existingDistributeur.get());
					demandeService.saveDemande(demande);
				}
			}
			
			return new ResponseEntity<>(object,HttpStatus.CREATED);
		}
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/update_demande")
	public ResponseEntity<Demande> updateDemande(@RequestBody Demande demande){
		
		System.out.println(demande);
		demandeService.saveDemande(demande);
		return new ResponseEntity<Demande>(demande,HttpStatus.OK);
	}
}
