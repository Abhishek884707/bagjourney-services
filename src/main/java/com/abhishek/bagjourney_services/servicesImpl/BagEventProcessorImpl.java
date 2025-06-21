package com.abhishek.bagjourney_services.servicesImpl;

import com.abhishek.bagjourney_services.dto.BagEvent;
import com.abhishek.bagjourney_services.entity.BagItinerary;
import com.abhishek.bagjourney_services.entity.BagTagEvents;
import com.abhishek.bagjourney_services.model.*;
import com.abhishek.bagjourney_services.services.BagEventProcessor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BagEventProcessorImpl implements BagEventProcessor {

    @Override
    public BagTagEvents process(BagEvent bagEvent) {
        EventDetails eventDetails = new EventDetails();
        // fetch call to DB get the details of Itinerary
        BagItinerary bagItinerary = null;
        if (bagItinerary == null){
            List<BagItinerary> bagItineraries = createBagItinerary(bagEvent);
            // fetch call to DB get the details of Itinerary after creating
            bagItinerary = new BagItinerary();
        }

        // create Event Details
        eventDetails = createEventDetails(bagEvent, bagItinerary);
        this.setEventDetails(eventDetails, bagEvent);


        return storeBagTagEvent(eventDetails, bagItinerary.getMasterBagId());
    }

    private BagTagEvents storeBagTagEvent(EventDetails eventDetails, String masterBagId) {
        BagTagEvents bagTagEvents = new BagTagEvents();
        bagTagEvents.setMasterBagId(masterBagId);
        bagTagEvents.setEventDetails(eventDetails);
        return bagTagEvents;
    }

    public List<BagItinerary> createBagItinerary(BagEvent bagEvent) {
        List<BagItinerary> bagItineraries = new ArrayList<>();
            String masterBagTagId = UUID.randomUUID().toString();
            BagItinerary itinerary = null;
            if(bagEvent.getOutbound() != null){
                itinerary = createItinerary(bagEvent.getOutbound(), bagEvent.getBagTag(),
                        bagEvent.getPassenger(), bagEvent.getPnr(), masterBagTagId);
                if (itinerary != null){
                    bagItineraries.add(itinerary);
                }
            }else if(bagEvent.getInbound() != null){
                itinerary = createItinerary(bagEvent.getInbound(), bagEvent.getBagTag(),
                        bagEvent.getPassenger(), bagEvent.getPnr(), masterBagTagId);
                if (itinerary != null){
                    bagItineraries.add(itinerary);
                }
            } else if (!bagEvent.getOnwards().isEmpty()) {
                for(FlightLeg flight : bagEvent.getOnwards()){
                    itinerary = createItinerary(flight, bagEvent.getBagTag(),
                            bagEvent.getPassenger(), bagEvent.getPnr(), masterBagTagId);
                    if (itinerary != null){
                        bagItineraries.add(itinerary);
                    }
                }
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

    private EventDetails createEventDetails(BagEvent bagEvent, BagItinerary bagItinerary){
        EventDetails eventDetails = new EventDetails();

        eventDetails.setAirport(bagEvent.getAirportCode());
        eventDetails.setBagTagNumber(bagEvent.getBagTag());
        eventDetails.setFrequentFlyerId(bagEvent.getLoyaltyNum());
        eventDetails.setInbound(bagEvent.getInbound());
        eventDetails.setOutbound(bagEvent.getOutbound());
        eventDetails.setOnwards(bagEvent.getOnwards());
        eventDetails.setMessageType(bagEvent.getMessageType());
        eventDetails.setBaggageSourceIndicator(bagEvent.getBaggageSourceIndicator());
        eventDetails.setPassenger(eventDetails.getPassenger());
        eventDetails.setPnr(bagEvent.getPnr());
        eventDetails.setWeight(bagEvent.getBagWeightDetail());
        eventDetails.setPaxStatus(bagEvent.getReconcilation().getPassengerStatus());
        eventDetails.setBagTagStatus(bagEvent.getReconcilation().getBagStatus());
        eventDetails.setSeatNumber(bagEvent.getReconcilation().getSeatNumber());

        return eventDetails;
    }

    private EventDetails setEventDetails(EventDetails eventDetails, BagEvent bagEvent){

        if(bagEvent.getEventCode() == EventCodes.C) {
            eventDetails.setEventCode(Constats.BAG_CHECKED_IN);
            eventDetails.setEventDescription("Bag Checked In at : " + bagEvent.getAirportCode());
        }else if(bagEvent.getEventCode() == EventCodes.DB){
            eventDetails.setEventCode(Constats.BAG_DAMAGED);
            eventDetails.setEventDescription("Damaged Bag Reported");
        }
        return  eventDetails;
    }
}
