package com.abhishek.bagjourney_services.dto;

import com.abhishek.bagjourney_services.model.Passenger;
import com.abhishek.bagjourney_services.model.WeightDetails;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class BagHistoryResponse {
    Boolean success;
    String bagTagNum;
    Passenger passenger;
    WeightDetails weightDetails;
    Reconciliation reconciliation;
    List<BagTagEvent> bagTagEvents;
}
