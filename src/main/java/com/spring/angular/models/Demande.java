package com.spring.angular.models;

import java.util.Date;

import com.spring.angular.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
	private Date date_demande;
	@Column(name = "type_demande")
	private String typeDemande;
	private int duree_abonnement;
	private double commission;
	private Date date_debutAbonnement;
	private Date date_finAbonnement;
	private boolean parabole;
	@Enumerated(EnumType.STRING)
	private Status status;
	private double montantDemande;
	@OneToOne
	@JoinColumn(name = "client_id")
	private Client client;
	@OneToOne
	@JoinColumn(name = "offre_id")
	private Offre offre;
	@ManyToOne(cascade = {CascadeType.ALL})
	private Distributeur distributeur;
}
