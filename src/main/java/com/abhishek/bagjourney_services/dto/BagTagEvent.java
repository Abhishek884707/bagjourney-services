package com.abhishek.bagjourney_services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BagTagEvent {

    String eventAirline;
    String eventStation;
    String previousHandlingAirline;
    String uplinkStation;
    String previousStation;
    String transferAirline;
    String nextStation;
    String onwardAirline;
    String downlineStations;
    String eventCode;
    String eventDescription;
    String connectingFlightInfo;
    String frequentFlyer;
    String localDateTime;
    String passengerStatus;
    String pnr;
    String bagStatus;
    FlightInformation flightInformation;

}
