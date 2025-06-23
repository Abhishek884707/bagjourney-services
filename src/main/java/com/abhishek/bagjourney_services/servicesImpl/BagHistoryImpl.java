package com.abhishek.bagjourney_services.servicesImpl;

import com.abhishek.bagjourney_services.entity.BagItinerary;
import com.abhishek.bagjourney_services.entity.BagTagEvents;
import com.abhishek.bagjourney_services.repository.BagTagEventsRepository;
import com.abhishek.bagjourney_services.repository.ItineraryRepository;
import com.abhishek.bagjourney_services.services.BagHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BagHistoryImpl implements BagHistory {

    @Autowired
    ItineraryRepository itineraryRepository;

    @Autowired
    BagTagEventsRepository bagTagEventsRepository;

    @Override
    public List<BagTagEvents> getListOfEvents(String bagTagNumber, String date, String lastName, String pnr) {

        BagItinerary bagItinerary = this.itineraryRepository.getItineraryWithPnr(bagTagNumber, pnr);

        if(bagItinerary != null){
            return this.bagTagEventsRepository.getBagTagEvents(bagItinerary.getMasterBagId());
        }

        return null;
    }

}
