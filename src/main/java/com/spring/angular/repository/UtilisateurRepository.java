package com.spring.angular.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.angular.models.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	
	public Optional<Utilisateur> findByNom(String nom);
	
	public Optional<Utilisateur> findByUsername(String username);
}
