package com.spring.angular.records;

import com.spring.angular.enums.Statut;

public record TestRecord(double comission, String date_demande,
 int duree,Statut statut,String typeDemande) {

}
