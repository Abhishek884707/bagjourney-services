package com.abhishek.bagjourney_services.dto;

import com.abhishek.bagjourney_services.model.WeightDetails;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BagHistoryResponse {
    Boolean success;
    String bagTagNum;
    String firstName;
    String lastName;
    WeightDetails weightDetails;
    String frequentFlyerId;
    List<Event> bagTagEvents;
}
