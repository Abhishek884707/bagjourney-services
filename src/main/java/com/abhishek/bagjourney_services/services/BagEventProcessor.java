package com.abhishek.bagjourney_services.services;

import com.abhishek.bagjourney_services.dto.BagEvent;
import com.abhishek.bagjourney_services.entity.BagTagEvents;

import java.util.List;

public interface BagEventProcessor {

    public List<BagTagEvents> createBagItenerary(BagEvent bagEvent);

}
