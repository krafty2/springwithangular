package com.spring.angular.models;

import com.spring.angular.enums.Promo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Offre {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long idLong;
	@Column(unique = true)
	private String bouquet;
	private double montant;
	@Enumerated(EnumType.STRING)
	private Promo promo;
}
