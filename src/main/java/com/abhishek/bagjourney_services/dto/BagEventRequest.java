package com.abhishek.bagjourney_services.dto;

import com.abhishek.bagjourney_services.model.EventCodes;
import com.abhishek.bagjourney_services.model.FlightLeg;
import com.abhishek.bagjourney_services.model.Passenger;
import com.abhishek.bagjourney_services.model.WeightDetails;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "Message type can't be null.")
    String messageType;

    @NotNull(message = "Airport Code can't be null.")
    @Size(min = 3, max = 3, message
            = "Airport Code must be 3 characters long.")
    String airportCode;

    @NotNull(message = "Baggage Source Indicator can't be null.")
    @Size(min = 1, max = 1, message
            = "Baggage Source Indicator must be 1 character.")
    String baggageSourceIndicator;

    @NotNull(message = "Bag Tag Number can't be null")
    @Size(min = 10, max = 10, message
            = "Bag Tag Number must be 10 characters long.")
    String bagTag;

    @Size(min = 6, max = 6, message
            = "PNR Number must be 6 characters long.")
    String pnr;

    FlightLeg outbound;
    FlightLeg inbound;
    List<FlightLeg> onwards;
    Passenger passenger;
    String frequentFlyerId;
    Reconciliation reconciliation;
    WeightDetails bagWeightDetail;

    @NotNull(message = "Event Code Number can't be null.")
    EventCodes eventCode;
    String DPReferenceNumber;
    String comments;
}
