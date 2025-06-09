package com.abhishek.bagjourney_services.dto;

import com.abhishek.bagjourney_services.model.FlightLeg;
import com.abhishek.bagjourney_services.model.Passenger;
import com.abhishek.bagjourney_services.model.WeightDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Data
public class BagEvent {

    String messageType;
    String airportCode;
    String baggageSourceIndicator;
    String bagTag;
    String locator;
    FlightLeg outbound;
    FlightLeg inbound;
    List<FlightLeg> onwards;
    Passenger passenger;
    Reconcilation reconcilation;
    WeightDetails bagWeightDetail;
    String loyaltyNum;
}
