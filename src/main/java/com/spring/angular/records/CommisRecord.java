package com.spring.angular.records;

import java.util.List;

import com.spring.angular.models.Demande;

public record CommisRecord(double commission,List<Demande> demandes) {

}
