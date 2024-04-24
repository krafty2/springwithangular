package com.spring.angular.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Distributeur extends Utilisateur {
//	private String codeDistributeur;
	private Double longitude;
	private Double latitude;
}
