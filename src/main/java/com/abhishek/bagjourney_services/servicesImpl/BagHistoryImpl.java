package com.abhishek.bagjourney_services.servicesImpl;

import com.abhishek.bagjourney_services.entity.BagItinerary;
import com.abhishek.bagjourney_services.entity.BagTagEvents;
import com.abhishek.bagjourney_services.repository.BagTagEventsRepository;
import com.abhishek.bagjourney_services.repository.ItineraryRepository;
import com.abhishek.bagjourney_services.services.BagHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BagHistoryImpl implements BagHistory {

    @Autowired
    ItineraryRepository itineraryRepository;

    @Autowired
    BagTagEventsRepository bagTagEventsRepository;

    @Override
    public List<BagTagEvents> getListOfEvents(String bagTagNumber, String date, String lastName, String pnr) {
        List<BagItinerary> bagItineraries = new ArrayList<>();
        if(bagTagNumber != null
                && !bagTagNumber.isBlank()
                && date != null
                && !date.isBlank()){
            bagItineraries = this.itineraryRepository.findByBagTagIdAndDate(bagTagNumber, date);
        }

        if(bagItineraries.isEmpty() && lastName != null && !lastName.isEmpty()){
            bagItineraries = this.itineraryRepository.findByBagTagIdAndDateAndLastNameIgnoreCase(bagTagNumber, date, lastName);
        }

        if(bagItineraries.isEmpty() &&
                pnr != null &&
                !pnr.isBlank()){
            bagItineraries = this.itineraryRepository.findByBagTagIdAndDateAndPnrNumberIgnoreCase(bagTagNumber
            ,date, pnr);
        }

        for(BagItinerary bagItinerary : bagItineraries){
            return this.bagTagEventsRepository.findByMasterBagIdOrderByEventDateAsc(bagItinerary.getMasterBagId());
        }

        return null;
    }
}
