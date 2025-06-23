package com.abhishek.bagjourney_services.services;

import com.abhishek.bagjourney_services.dto.BagEventRequest;

public interface BagEventProcessor {

    public Boolean process(BagEventRequest bagEvent);

}
