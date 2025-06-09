package com.abhishek.bagjourney_services.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeightDetails {

    String indicator;
    int numberOfCheckedBag;
    float checkedWeight;
    float length;
    float width;
    float height;

}
