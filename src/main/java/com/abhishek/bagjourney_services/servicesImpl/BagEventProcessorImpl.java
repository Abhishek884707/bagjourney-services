package com.abhishek.bagjourney_services.servicesImpl;

import com.abhishek.bagjourney_services.dto.BagEvent;
import com.abhishek.bagjourney_services.entity.BagTagEvents;
import com.abhishek.bagjourney_services.services.BagEventProcessor;

import java.util.List;

public class BagEventProcessorImpl implements BagEventProcessor {
    @Override
    public List<BagTagEvents> createBagItenerary(BagEvent bagEvent) {
        return List.of();
    }
}
