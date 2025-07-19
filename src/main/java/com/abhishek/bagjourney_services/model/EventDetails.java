package com.abhishek.bagjourney_services.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EventDetails {

    String airport;
    String eventDateTimeLocal;
    String eventCode;
    String eventDescription;
    String bagTagNumber;
    FlightLeg outbound;
    FlightLeg inbound;
    List<FlightLeg> onwards;
    String messageType;
    String baggageSourceIndicator;
    Passenger passenger;
    String pnr;
    String PaxStatus;
    WeightDetails weight;
    String bagTagStatus;
    String seatNumber;
    String comments;
    String referenceNumber;
    String frequentFlyerId;
}
