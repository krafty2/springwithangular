package com.spring.angular.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.angular.models.Offre;

@Repository
public interface OffreRepository extends JpaRepository<Offre, Long> {

	Optional<Offre> findByBouquet(String bouquet);
}
