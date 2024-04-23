package com.spring.angular.repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;

import com.spring.angular.models.Demande;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {

	@Query(value = "SELECT *\r\n"
			+ "FROM demande\r\n"
			+ "WHERE type_demande = 'recrutement';",nativeQuery = true)
	List<Demande> listeRecrutDemande();
}
