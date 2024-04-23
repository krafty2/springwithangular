package com.spring.angular.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spring.angular.models.Offre;
import com.spring.angular.repository.OffreRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
public class OffreService {
	
	private OffreRepository offreRepository;

	public OffreService(OffreRepository offreRepository) {
		super();
		this.offreRepository = offreRepository;
	}
	
	public Offre saveOffre(Offre offre) {
		return offreRepository.save(offre);
	}
	
	public List<Offre> listeOffre(){
		Sort sort = Sort.by("montant").ascending();
		return offreRepository.findAll(sort);
	}
	
	public Optional<Offre> searchOffre(Long id){
		return offreRepository.findById(id);
	}
	
	public Optional<Offre> searchOffreByBouquet(String bouquet){
		return offreRepository.findByBouquet(bouquet);
	}
	
	public void delete(Offre offre) {
		this.offreRepository.delete(offre);
	}

}
