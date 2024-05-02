package com.spring.angular.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.angular.enums.Status;
import com.spring.angular.models.Demande;
import com.spring.angular.models.Distributeur;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {

	@Query(value = "SELECT *\r\n"
			+ "FROM demande\r\n"
			+ "WHERE type_demande = ?1",nativeQuery = true)
	List<Object[]> listeRecrutDemande(String name);
	
	List<Demande> findByTypeDemande(String typeDemande);
	
	List<Demande> findByStatusAndTypeDemande(Status status,String typeDemande);
	
	List<Demande> findByTypeDemandeAndDistributeur(String typeDemande,Distributeur distributeur);
	
	@Query(value = "SELECT * \r\n"
			+ "FROM demande \r\n"
			+ "WHERE extract(month from date_demande)=?1\r\n"
			+ "AND extract(year from date_demande)=?2",nativeQuery = true)
	List<Object[]> searchList(Integer month,Integer year);
}
