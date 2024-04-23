package com.spring.angular.models;

import java.sql.Date;

import com.spring.angular.enums.Statut;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Demande {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long idLong;
	private String date_demande;
	private String type_demande;
	private int duree_abonnement;
	private double commission;
	private String date_debutAbonnement;
	private String date_finAbonnement;
	private boolean parabole;
	@Enumerated(EnumType.STRING)
	private Statut status;
	@OneToOne
	@JoinColumn(name = "client_id")
	private Client client;
	@OneToOne
	@JoinColumn(name = "offre_id")
	private Offre offre;
	@ManyToOne(cascade = {CascadeType.ALL})
	private Distributeur distributeur;
}
