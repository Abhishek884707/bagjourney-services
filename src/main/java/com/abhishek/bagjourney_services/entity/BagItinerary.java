package com.abhishek.bagjourney_services.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "bag_itinerary")
public class BagItinerary {

    @Id
    String id;
    String bagTagId;
    String date;
    String departedStation;
    String pnrNumber;
    String lastName;
    String masterBagId;

}
