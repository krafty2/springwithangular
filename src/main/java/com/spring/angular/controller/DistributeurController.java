package com.spring.angular.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.spring.angular.models.Distributeur;
import com.spring.angular.service.DistributeurService;

@RestController
@RequestMapping("/distrib/")
@CrossOrigin(origins = "*")
public class DistributeurController {
	
	private DistributeurService distributeurService;

	public DistributeurController(DistributeurService distributeurService) {
		super();
		this.distributeurService = distributeurService;
	}


	@GetMapping("/all_distrib")
	public List<Distributeur> allDistrib() {
		return distributeurService.allDistrib();
	}
	
	//PostMapping

	@PostMapping("/save_distributeur")
	public ResponseEntity<String> saveDistributeur(@RequestBody Distributeur distributeur) {
		
		if(distributeur!=null){
			distributeurService.saveDistributeur(distributeur);
			
			return new ResponseEntity(distributeur,HttpStatus.CREATED);
		}
		
		return ResponseEntity.ok().build();
	}
}
