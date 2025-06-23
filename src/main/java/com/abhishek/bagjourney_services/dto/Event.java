package com.abhishek.bagjourney_services.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Event {

    String eventAirline;
    String eventStation;
    String eventCode;
    String eventDescription;
    String frequentFlyer;
    String localDateTime;
    String passengerStatus;
    String pnr;
    String bagStatus;
    FlightInformation flightInformation;

}
