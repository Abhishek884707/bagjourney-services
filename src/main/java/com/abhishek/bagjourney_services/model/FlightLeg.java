package com.abhishek.bagjourney_services.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FlightLeg {

    String airportCode;
    String airlineCode;
    String flightNumber;
    String date;
    String direction;
}
