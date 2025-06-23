package com.abhishek.bagjourney_services.servicesImpl;

import com.abhishek.bagjourney_services.dto.BagEventRequest;
import com.abhishek.bagjourney_services.entity.BagItinerary;
import com.abhishek.bagjourney_services.entity.BagTagEvents;
import com.abhishek.bagjourney_services.model.*;
import com.abhishek.bagjourney_services.repository.BagTagEventsRepository;
import com.abhishek.bagjourney_services.repository.ItineraryRepository;
import com.abhishek.bagjourney_services.services.BagEventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BagEventProcessorImpl implements BagEventProcessor {

    @Autowired
    ItineraryRepository itineraryRepository;

    @Override
    public Boolean process(BagEventRequest bagEvent) {
        EventDetails eventDetails = new EventDetails();
        // fetch call to DB get the details of Itinerary
        BagItinerary bagItinerary = itineraryRepository.getItinerary(bagEvent.getBagTag(), bagEvent.getAirportCode());
        if (bagItinerary == null){
            List<BagItinerary> bagItineraries = createBagItinerary(bagEvent);
            // fetch call to DB get the details of Itinerary after creating
            bagItinerary = itineraryRepository.getItinerary(bagEvent.getBagTag(), bagEvent.getAirportCode());
        }

        // create Event Details
        eventDetails = createEventDetails(bagEvent, bagItinerary);
        this.setEventDetails(eventDetails, bagEvent);


        return storeBagTagEvent(eventDetails, bagItinerary.getMasterBagId());
    }


    private Boolean storeBagTagEvent(EventDetails eventDetails, String masterBagId) {
        SimpleDateFormat DDMMM = new SimpleDateFormat("ddMMM");
        Date date = new Date();
        String currentDate = DDMMM.format(date);
        BagTagEvents bagTagEvent = new BagTagEvents();
        bagTagEvent.setMasterBagId(masterBagId);
        bagTagEvent.setEventDetails(eventDetails);
        eventDetails.setEventDateTimeLocal(currentDate);
        bagTagEvent.setEventDate(currentDate);
        List<BagTagEvents> bagTagEventsList = BagTagEventsRepository.bagTagEventsList.get(masterBagId);
        if(bagTagEventsList != null && !bagTagEventsList.isEmpty()){
            List<BagTagEvents> bagTagEvents = new ArrayList<>();
            bagTagEvents.addAll(bagTagEventsList);
            bagTagEvents.add(bagTagEvent);
            BagTagEventsRepository.bagTagEventsList.put(masterBagId, bagTagEvents);
        }else{
            BagTagEventsRepository.bagTagEventsList.put(masterBagId, Arrays.asList(bagTagEvent));
        }
        return true;
    }

    public List<BagItinerary> createBagItinerary(BagEventRequest bagEvent) {
        List<BagItinerary> bagItineraries = new ArrayList<>();
            String masterBagTagId = UUID.randomUUID().toString();
            BagItinerary itinerary = null;
            if(bagEvent.getAirportCode() != null){
            }
            if(bagEvent.getOutbound() != null && bagEvent.getOutbound().getAirportCode() != null){
                itinerary = createItinerary(bagEvent.getOutbound(), bagEvent.getBagTag(),
                        bagEvent.getPassenger(), bagEvent.getPnr(), masterBagTagId);
                if (itinerary != null){
                    bagItineraries.add(itinerary);
                }
                //create itinerary with the source station details
                FlightLeg flight = bagEvent.getOutbound();
                flight.setAirportCode(bagEvent.getAirportCode());
                itinerary = createItinerary(bagEvent.getOutbound(), bagEvent.getBagTag(),
                        bagEvent.getPassenger(), bagEvent.getPnr(), masterBagTagId);
                if (itinerary != null){
                    bagItineraries.add(itinerary);
                }
            }if(bagEvent.getInbound() != null && bagEvent.getInbound().getAirportCode() != null){
                itinerary = createItinerary(bagEvent.getInbound(), bagEvent.getBagTag(),
                        bagEvent.getPassenger(), bagEvent.getPnr(), masterBagTagId);
                if (itinerary != null){
                    bagItineraries.add(itinerary);
                }
            } if (!bagEvent.getOnwards().isEmpty()) {
                for(FlightLeg flight : bagEvent.getOnwards()){
                    itinerary = createItinerary(flight, bagEvent.getBagTag(),
                            bagEvent.getPassenger(), bagEvent.getPnr(), masterBagTagId);
                    if (itinerary != null){
                        bagItineraries.add(itinerary);
                    }
                }
            }

         if(!bagItineraries.isEmpty()){
             ItineraryRepository.bagItineraries.addAll(bagItineraries);
         }

        return bagItineraries;
    }

    private BagItinerary createItinerary(FlightLeg flight, String bagTag, Passenger passenger,
                                         String pnr, String masterBagTagId){
        BagItinerary bagItinerary = null;
        if(flight.getAirportCode() != null){
            bagItinerary = BagItinerary.builder().masterBagId(masterBagTagId.toString()).
                    bagTagId(bagTag).
            departedStation(flight.getAirportCode()).
            pnrNumber(pnr).
            lastName(passenger.getLastName()).
            date(flight.getDate()).build();
        }
        return bagItinerary;
    }

    private EventDetails createEventDetails(BagEventRequest bagEvent, BagItinerary bagItinerary){
        EventDetails eventDetails = new EventDetails();
        eventDetails.setAirport(bagEvent.getAirportCode());
        eventDetails.setBagTagNumber(bagEvent.getBagTag());
        eventDetails.setFrequentFlyerId(bagEvent.getLoyaltyNum());
        eventDetails.setInbound(bagEvent.getInbound());
        eventDetails.setOutbound(bagEvent.getOutbound());
        eventDetails.setOnwards(bagEvent.getOnwards());
        eventDetails.setMessageType(bagEvent.getMessageType());
        eventDetails.setBaggageSourceIndicator(bagEvent.getBaggageSourceIndicator());
        eventDetails.setPassenger(bagEvent.getPassenger());
        eventDetails.setPnr(bagEvent.getPnr());
        eventDetails.setWeight(bagEvent.getBagWeightDetail());
        eventDetails.setPaxStatus(bagEvent.getReconciliation().getPassengerStatus());
        eventDetails.setBagTagStatus(bagEvent.getReconciliation().getBagStatus());
        eventDetails.setSeatNumber(bagEvent.getReconciliation().getSeatNumber());

        return eventDetails;
    }

    private EventDetails setEventDetails(EventDetails eventDetails, BagEventRequest bagEvent){

        if(bagEvent.getEventCode() == EventCodes.C) {
            if(Constats.T_SOURCE_INDICATOR.equalsIgnoreCase(bagEvent.getBaggageSourceIndicator()))
                eventDetails.setEventCode(Constats.BAG_EXPECTED);
            else
                eventDetails.setEventCode(Constats.BAG_CHECKED_IN);

            eventDetails.setEventDescription("Bag Checked In");
        }else if(bagEvent.getEventCode() == EventCodes.DB){
            eventDetails.setEventCode(Constats.BAG_DAMAGED);
            eventDetails.setEventDescription("Damaged Bag Reported");
        }
        return  eventDetails;
    }
}
