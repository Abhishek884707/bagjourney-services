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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class BagEventProcessorImpl implements BagEventProcessor {

    @Autowired
    ItineraryRepository itineraryRepository;

    @Autowired
    BagTagEventsRepository bagTagEventsRepository;

    @Override
    public Boolean process(BagEventRequest bagEvent) {
        EventDetails eventDetails = new EventDetails();
        // fetch call to DB get the details of Itinerary
        BagItinerary bagItinerary = itineraryRepository.findByBagTagIdAndDepartedStation(bagEvent.getBagTag(), bagEvent.getAirportCode());
        if (bagItinerary == null){
            List<BagItinerary> bagItineraries = createBagItinerary(bagEvent);
            // fetch call to DB get the details of Itinerary after creating
            bagItinerary = itineraryRepository.findByBagTagIdAndDepartedStation(bagEvent.getBagTag(), bagEvent.getAirportCode());
        }

        // create Event Details
        eventDetails = createEventDetails(bagEvent, bagItinerary);
        this.setEventDetails(eventDetails, bagEvent);


        return storeBagTagEvent(eventDetails, bagItinerary.getMasterBagId());
    }


    private Boolean storeBagTagEvent(EventDetails eventDetails, String masterBagId) {
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
        LocalDateTime localDateTime = LocalDateTime.now();
        String currentDate = localDateTime.format(formatter);
        BagTagEvents bagTagEvent = new BagTagEvents();
        bagTagEvent.setMasterBagId(masterBagId);
        bagTagEvent.setEventDetails(eventDetails);
        eventDetails.setEventDateTimeLocal(currentDate);
        bagTagEvent.setEventDate(currentDate);
        bagTagEventsRepository.save(bagTagEvent);
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
                FlightLeg flight = new FlightLeg();
                flight.setAirportCode(bagEvent.getAirportCode());
                flight.setAirlineCode(bagEvent.getOutbound().getAirlineCode());
                flight.setDate(bagEvent.getOutbound().getDate());
                flight.setFlightNumber(bagEvent.getOutbound().getFlightNumber());
                itinerary = createItinerary(flight, bagEvent.getBagTag(),
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
             itineraryRepository.saveAll(bagItineraries);
         }

        return bagItineraries;
    }

    private BagItinerary createItinerary(FlightLeg flight, String bagTag, Passenger passenger,
                                         String pnr, String masterBagTagId){
        BagItinerary bagItinerary = null;
        if(flight.getAirportCode() != null){
            bagItinerary = new BagItinerary();
            bagItinerary.setDate(flight.getDate());
            bagItinerary.setBagTagId(bagTag);
            bagItinerary.setMasterBagId(masterBagTagId.toString());
            bagItinerary.setLastName(passenger.getLastName());
            bagItinerary.setPnrNumber(pnr);
            bagItinerary.setDepartedStation(flight.getAirportCode());
        }
        return bagItinerary;
    }

    private EventDetails createEventDetails(BagEventRequest bagEvent, BagItinerary bagItinerary){
        EventDetails eventDetails = new EventDetails();
        eventDetails.setAirport(bagEvent.getAirportCode());
        eventDetails.setBagTagNumber(bagEvent.getBagTag());
        eventDetails.setFrequentFlyerId(bagEvent.getFrequentFlyerId());
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
        eventDetails.setComments(bagEvent.getComments());
        eventDetails.setReferenceNumber(bagEvent.getDPReferenceNumber());
        return eventDetails;
    }

    private EventDetails setEventDetails(EventDetails eventDetails, BagEventRequest bagEvent){

        if(bagEvent.getEventCode() == EventCodes.C) {
            if(Constats.T_SOURCE_INDICATOR.equalsIgnoreCase(bagEvent.getBaggageSourceIndicator()))
                eventDetails.setEventDescription(Constats.BAG_EXPECTED);
            else
                eventDetails.setEventDescription(Constats.BAG_CHECKED_IN);

            eventDetails.setEventCode(Constats.BAG_CHECKED_IN);
        }else if(bagEvent.getEventCode() == EventCodes.DB){
            eventDetails.setEventCode(Constats.BAG_DAMAGED);
            eventDetails.setEventDescription(Constats.BAG_DAMAGED);
        }else if(bagEvent.getEventCode() == EventCodes.DBU){
            eventDetails.setEventCode(Constats.DAMAGED_BAG_UPDATED);
            eventDetails.setEventDescription(Constats.DAMAGED_BAG_UPDATED);
        }else if(bagEvent.getEventCode() == EventCodes.DBDU){
            eventDetails.setEventCode(Constats.DAMAGED_BAG_DELIVERY_UNDERWAY);
            eventDetails.setEventDescription(Constats.DAMAGED_BAG_DELIVERY_UNDERWAY);
        }else if(bagEvent.getEventCode() == EventCodes.DBC){
            eventDetails.setEventCode(Constats.DAMAGED_BAG_COLLECTED);
            eventDetails.setEventDescription(Constats.DAMAGED_BAG_COLLECTED);
        }else if(bagEvent.getEventCode() == EventCodes.DBDS){
            eventDetails.setEventCode(Constats.DAMAGED_BAG_DELIVERY_SCHEDULED);
            eventDetails.setEventDescription(Constats.DAMAGED_BAG_DELIVERY_SCHEDULED);
        }else if(bagEvent.getEventCode() == EventCodes.DBDC){
            eventDetails.setEventCode(Constats.DAMAGED_BAG_DELIVERY_CONFIRMED);
            eventDetails.setEventDescription(Constats.DAMAGED_BAG_DELIVERY_CONFIRMED);
        }else if(bagEvent.getEventCode() == EventCodes.DBR){
            eventDetails.setEventCode(Constats.DAMAGED_BAG_RECEIVED);
            eventDetails.setEventDescription(Constats.DAMAGED_BAG_RECEIVED);
        }else if(bagEvent.getEventCode() == EventCodes.CDBC){
            eventDetails.setEventCode(Constats.CONFIRM_DAMAGED_BAG_CLAIM);
            eventDetails.setEventDescription(Constats.CONFIRM_DAMAGED_BAG_CLAIM);
        }else if(bagEvent.getEventCode() == EventCodes.DBRC){
            eventDetails.setEventCode(Constats.DAMAGED_BAG_REPORT_CLOSED);
            eventDetails.setEventDescription(Constats.DAMAGED_BAG_REPORT_CLOSED);
        }
        return  eventDetails;
    }
}
