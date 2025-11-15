package com.abhishek.bagjourney_services.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {

    String eventAirline;
    String eventStation;
    String eventCode;
    String eventDescription;
    String localDateTime;
    String passengerStatus;
    String baggageSourceIndicator;
    String pnr;
    String bagStatus;
    FlightInformation flightInformation;
    String referenceNumber;
    String comments;
    String seatNumber;

}
