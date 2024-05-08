package com.spring.angular.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.spring.angular.models.Distributeur;
import com.spring.angular.repository.DistributeurRepository;

@Service
public class DistributeurService {

	private DistributeurRepository distributeurRepository;
	
	

	public DistributeurService(DistributeurRepository distributeurRepository) {
		super();
		this.distributeurRepository = distributeurRepository;
	}
	
	public Distributeur saveDistributeur(Distributeur distributeur) {
		return distributeurRepository.save(distributeur);
	}
	
	public Optional<Distributeur> searchDistribById(Long id){
		return distributeurRepository.findById(id);
	}
	
	public Optional<Distributeur> searchDistribByCode(String code){
		return distributeurRepository.findByCodeDistributeur(code);
	}
	
	public List<Distributeur> allDistrib(){
		return distributeurRepository.findAll();
	}
}
