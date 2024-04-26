package com.spring.angular.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.angular.models.Gerant;
import com.spring.angular.repository.GerantRepository;

@Service
public class GerantService {

	@Autowired
	private GerantRepository gerantRepository;
	
	public Gerant saveGerant(Gerant gerant) {
		return gerantRepository.save(gerant);
	}
	
	public Optional<Gerant> searchGerant(Long id){
		return gerantRepository.findById(id);
	}
	
	public Optional<Gerant> searchGerantByCode(String code){
		return gerantRepository.findByCodeGerant(code);
	}
}
