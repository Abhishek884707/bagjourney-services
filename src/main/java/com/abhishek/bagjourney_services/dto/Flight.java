package com.abhishek.bagjourney_services.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Flight {

    String destinationAirport;
    String flightNumber;
    String date;
    String airline;
}
