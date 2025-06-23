package com.abhishek.bagjourney_services.dto;

import com.abhishek.bagjourney_services.model.Passenger;
import com.abhishek.bagjourney_services.model.WeightDetails;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BagHistoryResponse {
    Boolean success;
    String bagTagNum;
    String passengerName;
    WeightDetails weightDetails;
    Reconciliation reconciliation;
    List<Event> bagTagEvents;
}
