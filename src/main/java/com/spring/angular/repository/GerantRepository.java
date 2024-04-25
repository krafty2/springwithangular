package com.spring.angular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.angular.models.Gerant;

@Repository
public interface GerantRepository extends JpaRepository<Gerant, Long> {

}
