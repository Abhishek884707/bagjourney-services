package com.abhishek.bagjourney_services.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Reconciliation {

    String passengerStatus;
    String bagStatus;
    String seatNumber;
}
