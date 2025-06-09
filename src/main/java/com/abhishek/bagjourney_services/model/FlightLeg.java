package com.abhishek.bagjourney_services.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightLeg {

    String airportCode;
    String airlineCode;
    String flightNum;
    String date;
    String direction;
}
