package com.abhishek.bagjourney_services.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reconciliation {

    String passengerStatus;
    String bagStatus;
    String seatNumber;
}
