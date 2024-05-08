package com.spring.angular.controller;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.angular.enums.AccountStatus;
import com.spring.angular.models.Demande;
import com.spring.angular.models.Distributeur;
import com.spring.angular.models.Role;
import com.spring.angular.models.Utilisateur;
import com.spring.angular.records.CommisRecord;
import com.spring.angular.service.CanalService;
import com.spring.angular.service.DemandeService;
import com.spring.angular.service.DistributeurService;
import com.spring.angular.service.RoleService;
import com.spring.angular.service.UtilisateurService;

@RestController
@RequestMapping("/distrib/")
@CrossOrigin(origins = "*")
public class DistributeurController {
	
	@Autowired
	private DistributeurService distributeurService;
	
	@Autowired
	private DemandeService demandeService;
	
	@Autowired
	private UtilisateurService utilisateurService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CanalService canalService;


	//GetMapping
	
	//liste de tout les distributeurs
	@GetMapping("/all_distrib")
	public List<Distributeur> allDistrib() {
		return distributeurService.allDistrib();
	}
	
	//liste des demandes par distributeurs
	@GetMapping("/distrib_demande/{typeDemande}")
	public List<Demande> allDemande(Authentication authentication, @PathVariable String typeDemande){
		Distributeur distributeur = (Distributeur) utilisateurService
									.searchByUserName(authentication.getName())
									.get();
		
		return demandeService.listeDemandeDistrib(typeDemande, distributeur);
	}
	
	@GetMapping("/commission")
	public CommisRecord commission(Authentication authentication) {
		return canalService.commissionDistrib(authentication);
	}
	
	//PostMapping

	@PostMapping("/save_distributeur")
	public ResponseEntity<String> saveDistributeur(@RequestBody Distributeur distributeur) {
		
		if(distributeur!=null){
			
			String roleDistrib = "Distributeur";
			
			Optional<Role> existingRoleDistrib = roleService.searchByRoleName(roleDistrib);
			
			Role role = new Role();
			
			if(existingRoleDistrib.isEmpty()) {
        		
        		role.setRoleName(roleDistrib);
        		roleService.saveRole(role);
        	} else {
        		role = existingRoleDistrib.get();
        	}
			
			String password = distributeur.getPassword();
			
			distributeur.setPassword(passwordEncoder.encode(password));
			
			String randomString = RandomStringUtils.randomAlphanumeric(5);
			
			String codeDistrib = "D" + randomString;
			
			Optional<Distributeur> existingDistributueur = distributeurService.searchDistribByCode(codeDistrib);
			
			while (existingDistributueur.isPresent()) {
				randomString = RandomStringUtils.randomAlphanumeric(5);
				codeDistrib = "D" + randomString;
				existingDistributueur = distributeurService.searchDistribByCode(codeDistrib);
			}
			
			distributeur.setCodeDistributeur(codeDistrib);
			
			distributeur.getRoles().add(role);
			
			distributeur.setAccountStatus(AccountStatus.ACTIVATED);
			
			distributeurService.saveDistributeur(distributeur);
			
			return new ResponseEntity(distributeur,HttpStatus.CREATED);
		}
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/update_distributeur")
	public ResponseEntity<Distributeur> updateDistrib(@RequestBody Distributeur distributeur){
		if(distributeur!=null) {
			
			String password = distributeur.getPassword();
			
			Optional<Utilisateur> existingUtilisateur = utilisateurService
					.searchByUserName(distributeur.getUsername());
			
			Optional<Distributeur> existingDistrib = distributeurService.searchDistribByCode(distributeur.getCodeDistributeur());
				
			if(!password.equals(existingUtilisateur.get().getPassword())) {
				distributeur.setPassword(passwordEncoder.encode(password));
			}
			
			distributeurService.saveDistributeur(distributeur);
			System.out.println(distributeur);
			return new ResponseEntity<Distributeur>(distributeur,HttpStatus.OK);
		}
		
		return new ResponseEntity<Distributeur>(distributeur,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
