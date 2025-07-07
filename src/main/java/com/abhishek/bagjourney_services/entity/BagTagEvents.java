package com.abhishek.bagjourney_services.entity;

import com.abhishek.bagjourney_services.model.EventDetails;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "bag_tag_events")
public class BagTagEvents {
    @Id
    String id;
    String masterBagId;
    String eventDate;
    EventDetails eventDetails;
}
