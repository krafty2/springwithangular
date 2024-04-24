package com.spring.angular.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.angular.SpringwithangularApplication;
import com.spring.angular.models.Offre;
import com.spring.angular.service.OffreService;

@RestController
@RequestMapping("/offre/")
@CrossOrigin(origins = "*")
public class OffreController {

	private OffreService offreService;
	
	private static final Logger log = LoggerFactory.getLogger(SpringwithangularApplication.class);

	public OffreController(OffreService offreService) {
		super();
		this.offreService = offreService;
	}
	
	@PostMapping("/save_offre")
	public Offre offre(@RequestBody Offre offre) {
		
//		if(Promo.STANDARD == offre.getPromo()) {
//			System.out.println("True");
//			offre.setPromo(Promo.STANDARD);
//		} else {
//			offre.setPromo(Promo.SPECIAL);
//		}
		System.out.println(offre);
		
		return offreService.saveOffre(offre);
	}
	
	@GetMapping("/list_offre")
	public List<Offre> listOffre(){
		return offreService.listeOffre();
	}
	
	@DeleteMapping("/delete_offre/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id){
		Optional<Offre> existingOffre= offreService.searchOffre(id);
		
		if(existingOffre.isPresent()) {
			offreService.delete(existingOffre.get());
			log.info("delete offre " + existingOffre.get().getBouquet());
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
