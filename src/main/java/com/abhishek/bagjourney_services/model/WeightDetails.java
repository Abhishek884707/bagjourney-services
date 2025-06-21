package com.abhishek.bagjourney_services.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeightDetails {

    String indicator;
    int numberOfCheckedBag;
    float checkedWeight;
    float length;
    float width;
    float height;

}
