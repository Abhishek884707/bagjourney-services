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
    String eventType;
    String eventDescription;
    String bagTagNumber;
    String loadSequenceNumber;
    String bim;
    FlightLeg outbound;
    FlightLeg inbound;
    List<FlightLeg> onwards;
    String frequentFlyerId;
    String messageType;
    String baggageSourceIndicator;
    Passenger passenger;
    String pnr;
    String readLocationId;
    String sentLocationId;
    String PaxStatus;
    String aircraftCompartmentId;
    String stowageDeviceId;
    FlightLeg loadingFlightInformation;
    FlightLeg connectingFLightInformation;
    WeightDetails weight;
    String bagTagStatus;
    String authorityToLoad;
    String authorityToTransport;
    String seatNumber;
}
