package com.spring.angular.records;

import com.spring.angular.enums.Status;

public record TestRecord(double comission, String date_demande,
 int duree,Status statut,String typeDemande) {

}
