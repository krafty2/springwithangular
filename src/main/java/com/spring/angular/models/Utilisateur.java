package com.spring.angular.models;

import java.util.ArrayList;
import java.util.List;

import com.spring.angular.enums.AccountStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Utilisateur {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long idLong;
	private String username;
	private String nom;
	private String prenom;
	private String telephone;
	private String localite;
	private String password;
	@Enumerated(EnumType.STRING)
	private AccountStatus accountStatus;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles = new ArrayList<>();
}
