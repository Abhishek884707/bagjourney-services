package com.abhishek.bagjourney_services.services;

import com.abhishek.bagjourney_services.dto.BagEvent;
import com.abhishek.bagjourney_services.entity.BagTagEvents;

public interface BagEventProcessor {

    public BagTagEvents process(BagEvent bagEvent);

}
