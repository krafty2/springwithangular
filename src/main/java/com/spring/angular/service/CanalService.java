package com.spring.angular.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.spring.angular.enums.Status;
import com.spring.angular.models.Demande;
import com.spring.angular.models.Distributeur;
import com.spring.angular.records.CommisRecord;

@Service
public class CanalService {
	@Autowired
	private DemandeService demandeService;
	
	@Autowired
	private UtilisateurService utilisateurService;
	
	private double chiffreAffaireGerant = 0;
	
	private double commission;

	//retourn le chiffre d'affaire globale du mois du gerant
	public double chiffreAffaire() {
		chiffreAffaireGerant = 0;
		
		String pattern = "MM";
		String patternYY = "yyyy";
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		SimpleDateFormat simpleDateFormatYYY = new SimpleDateFormat(patternYY);
		
		Integer mois = Integer.parseInt(simpleDateFormat.format(new Date())) ;
		
		Integer year = Integer.parseInt(simpleDateFormatYYY.format(new Date()));
		
		List<Object[]> demandeMois = demandeService.searchByDateDemandes(mois, year);
		
		demandeMois.stream().forEach((demande)->{
			
			//System.out.println(demande[6]);
			Long idLong = (Long) demande[0];
			
			double montant = (double) demande[6];
			
			System.out.println(montant);
			
			chiffreAffaireGerant = chiffreAffaireGerant + montant;
			//System.out.println(demandeService.searchById(idLong).get());
		});
	
		return chiffreAffaireGerant;
	}
	
	public CommisRecord commissionDistrib(Authentication authentication) {
		commission = 0;
		
		Distributeur distributeur = (Distributeur) utilisateurService
										.searchByUserName(authentication.getName()).get();
		
		List<Demande> allDemandes = demandeService.saerchByDistrib(distributeur,Status.VALIDE);
		
		List<Demande> demandesValide = new ArrayList<>();
		
		String pattern = "MM";
		String patternYY = "yyyy";
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		SimpleDateFormat simpleDateFormatYYY = new SimpleDateFormat(patternYY);
		
		Integer month = Integer.parseInt(simpleDateFormat.format(new Date())) ;
		Integer year = Integer.parseInt(simpleDateFormatYYY.format(new Date()));
		
		for(Demande demande : allDemandes) {
			
			Integer monthDemande = Integer.parseInt(simpleDateFormat.format(demande.getDate_demande()));
			Integer yearDemande = Integer.parseInt(simpleDateFormatYYY.format(demande.getDate_demande()));
			
			System.out.println(year.equals(yearDemande));
			
			if(month.equals(monthDemande) && year.equals(yearDemande) ) {
				demandesValide.add(demande);
				commission = demande.getCommission() + commission;
			
			}
		}
		
		CommisRecord commisRecord = new CommisRecord(commission,demandesValide);
		
		return commisRecord;
	}
	
	
}
