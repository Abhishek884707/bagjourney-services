package com.abhishek.bagjourney_services.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BagItinerary {

    String bagTagId;
    String date;
    String departedStation;
    String pnrNumber;
    String lastName;
    String masterBagId;

}
