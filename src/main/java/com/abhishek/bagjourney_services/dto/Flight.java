package com.abhishek.bagjourney_services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

    String destinationAirport;
    String flightNumber;
    String date;
    String airline;
}
