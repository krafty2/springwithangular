package com.spring.angular.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Utilisateur {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long idLong;
	private String nom;
	private String prenom;
	private String telephone;
}
