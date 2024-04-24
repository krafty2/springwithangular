package com.spring.angular.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.angular.models.Demande;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {

	@Query(value = "SELECT *\r\n"
			+ "FROM demande\r\n"
			+ "WHERE type_demande = ?1",nativeQuery = true)
	List<Object[]> listeRecrutDemande(String name);
	
	List<Demande> findByTypeDemande(String typeDemande);
}
