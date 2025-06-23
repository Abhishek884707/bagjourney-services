package com.abhishek.bagjourney_services.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BagEventResponse {

    Boolean status;
    String message;

}
