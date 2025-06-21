package com.abhishek.bagjourney_services.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventDetails {

    String airport;
    String eventDateTimeLocal;
    String eventCode;
    String eventDescription;
    String bagTagNumber;
    String loadSequenceNumber;
    FlightLeg outbound;
    FlightLeg inbound;
    List<FlightLeg> onwards;
    String frequentFlyerId;
    String messageType;
    String baggageSourceIndicator;
    Passenger passenger;
    String pnr;
    String PaxStatus;
    WeightDetails weight;
    String bagTagStatus;
    String seatNumber;
}
