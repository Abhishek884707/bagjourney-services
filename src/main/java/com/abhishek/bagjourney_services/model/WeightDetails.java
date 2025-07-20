package com.abhishek.bagjourney_services.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeightDetails {

    @NotNull(message = "Weight Indicator can't be null")
    String indicator;

    @NotNull(message = "Number Of Checked Bag can't be null")
    @Positive(message = "Number Of Checked Bag can't be Negative or Zero")
    int numberOfCheckedBag;

    @Positive(message = "Checked Weight can't be Negative or Zero")
    float checkedWeight;

    @Positive(message = "Bag Length can't be Negative or Zero")
    float length;

    @Positive(message = "Bag Width can't be Negative or Zero")
    float width;

    @Positive(message = "Bag height can't be Negative or Zero")
    float height;

}
