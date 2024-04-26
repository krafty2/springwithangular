package com.spring.angular.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.angular.models.Distributeur;

@Repository
public interface DistributeurRepository extends JpaRepository<Distributeur, Long> {

	public Optional<Distributeur> findByCodeDistributeur(String codeDistributeur);
}
