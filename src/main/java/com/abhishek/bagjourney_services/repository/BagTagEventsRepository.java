package com.abhishek.bagjourney_services.repository;

import com.abhishek.bagjourney_services.entity.BagTagEvents;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BagTagEventsRepository {

    public static Map<String, List<BagTagEvents>> bagTagEventsList = new HashMap<>();

    public List<BagTagEvents> getBagTagEvents(String masterBagTagId){
        List<BagTagEvents> btEvents = new ArrayList<>();
        List<BagTagEvents> events = bagTagEventsList.get(masterBagTagId);
        if(!events.isEmpty()){
            return events;
        }
        return null;
    }
}
