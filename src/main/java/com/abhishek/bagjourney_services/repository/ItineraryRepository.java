package com.abhishek.bagjourney_services.repository;

import com.abhishek.bagjourney_services.entity.BagItinerary;
import com.abhishek.bagjourney_services.entity.BagTagEvents;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ItineraryRepository {

    public static Set<BagItinerary> bagItineraries = new HashSet<>();

    public BagItinerary getItinerary(String bagTagId, String airportCode){

        for(BagItinerary bagItinerary :  bagItineraries){
            if(bagItinerary.getBagTagId().equalsIgnoreCase(bagTagId)
                && bagItinerary.getDepartedStation().equalsIgnoreCase(airportCode)){
                return bagItinerary;
            }
        }
    return null;
    }

    public BagItinerary getItineraryWithPnr(String bagTagId, String pnr){

        for(BagItinerary bagItinerary :  bagItineraries){
            if(bagItinerary.getBagTagId().equalsIgnoreCase(bagTagId)
                    && bagItinerary.getPnrNumber().equalsIgnoreCase(pnr)){
                return bagItinerary;
            }
        }
        return null;
    }
}
