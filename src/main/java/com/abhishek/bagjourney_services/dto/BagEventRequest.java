package com.abhishek.bagjourney_services.dto;

import com.abhishek.bagjourney_services.model.EventCodes;
import com.abhishek.bagjourney_services.model.FlightLeg;
import com.abhishek.bagjourney_services.model.Passenger;
import com.abhishek.bagjourney_services.model.WeightDetails;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BagEventRequest {

    String messageType;
    String airportCode;
    String baggageSourceIndicator;
    String bagTag;
    String pnr;
    FlightLeg outbound;
    FlightLeg inbound;
    List<FlightLeg> onwards;
    Passenger passenger;
    String frequentFlyerId;
    Reconciliation reconciliation;
    WeightDetails bagWeightDetail;
    EventCodes eventCode;
    String DPReferenceNumber;
    String comments;
}
