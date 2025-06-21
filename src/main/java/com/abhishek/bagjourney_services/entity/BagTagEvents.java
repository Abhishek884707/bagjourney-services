package com.abhishek.bagjourney_services.entity;

import com.abhishek.bagjourney_services.model.EventDetails;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BagTagEvents {
    String masterBagId;
    String eventDate;
    EventDetails eventDetails;
}
