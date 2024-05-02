package com.spring.angular.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Gerant extends Utilisateur {
	@Column(unique = true)
	private String codeGerant;
}
