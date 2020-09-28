package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.util.InputReaderUtil;

public class FareCalculatorService {
	

    public void calculateFare(Ticket ticket, boolean isRecurent){
        if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ){
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }

        //TODO: Corréctions efféctuées !!
       // On obtient le temps passé dans le parking en Millis
       double duration = ticket.getOutTime().getTime() - ticket.getInTime().getTime();
       
       //On divise le resultat par 3 600 000 qui equivaut à une heure en Millis afin de le convertir sur une base 1. 
       double difference = duration / 3600000; 

       //Mise á zéro de la difference pour permettre au parking d'etre gratuit si inferieur à 30mn
       if(difference <= 0.5 ) {
           difference = 0;
       }
       
       
        switch (ticket.getParkingSpot().getParkingType()){
            case CAR: {
        
            	//Ajout d'une condtions ternaire afin d'ajouter 5% de reduction au utilisateur regulier.
                ticket.setPrice((isRecurent == false) ? difference * Fare.CAR_RATE_PER_HOUR : difference * Fare.CAR_RATE_PER_HOUR * 0.95);
                break;
            }
            case BIKE: {
            	//Ajout d'une condtions ternaire afin d'ajouter 5% de reduction au utilisateur regulier.
                ticket.setPrice((isRecurent == false) ? difference * Fare.BIKE_RATE_PER_HOUR : difference * Fare.BIKE_RATE_PER_HOUR * 0.95);
                break;
            }
            default: throw new IllegalArgumentException("Unkown Parking Type");
        }
    }
}