package com.abhishek.bagjourney_services.utility;

import com.abhishek.bagjourney_services.dto.*;
import com.abhishek.bagjourney_services.entity.BagTagEvents;
import com.abhishek.bagjourney_services.model.Constats;
import com.abhishek.bagjourney_services.model.EventDetails;
import com.abhishek.bagjourney_services.model.FlightLeg;

import java.util.ArrayList;
import java.util.List;

public class BagHistoryTransformer {

    public static BagHistoryResponse transform(List<BagTagEvents> bagTagEvents){
        BagHistoryResponse bagHistoryResponse = new BagHistoryResponse();
        List<Event> events = new ArrayList<>();
        if(bagTagEvents != null && !bagTagEvents.isEmpty()){
            updatePassengerDetails(bagHistoryResponse, bagTagEvents);
            EventDetails eventDetails = null;
            for(BagTagEvents bagTagEvent : bagTagEvents){
                eventDetails = bagTagEvent.getEventDetails();
                Event event = new Event();
                event.setEventStation(eventDetails.getAirport());
                event.setEventCode(eventDetails.getEventCode());
                event.setEventDescription(eventDetails.getEventDescription());
                setFlightInfo(event, eventDetails);
                event.setFrequentFlyer(eventDetails.getFrequentFlyerId());
                event.setPnr(eventDetails.getPnr());
                event.setLocalDateTime(eventDetails.getEventDateTimeLocal());
                event.setPassengerStatus(eventDetails.getPaxStatus());
                event.setBagStatus(eventDetails.getBagTagStatus());
                events.add(event);
            }
            bagHistoryResponse.setSuccess(true);
            bagHistoryResponse.setReconciliation(Reconciliation.builder()
                    .bagStatus(eventDetails.getBagTagStatus())
                    .passengerStatus(eventDetails.getPaxStatus())
                    .seatNumber(eventDetails.getSeatNumber())
                    .build());
            bagHistoryResponse.setBagTagEvents(events);
        }else{
            bagHistoryResponse.setSuccess(false);
        }
        return bagHistoryResponse;
    }

    static void setFlightInfo(Event event, EventDetails eventDetails) {

        FlightInformation flightInformation = new FlightInformation();

        if(eventDetails.getOutbound().getAirportCode() != null){
            flightInformation.setOutbound(Flight.builder().
                    date(eventDetails.getOutbound().getDate())
                    .flightNumber(eventDetails.getOutbound().getFlightNumber())
                    .destinationAirport(eventDetails.getOutbound().getAirportCode())
                    .airline(eventDetails.getOutbound().getAirlineCode())
                    .build());
        }
        if(eventDetails.getInbound().getAirportCode() != null){
            flightInformation.setInbound((InboundFlight.builder().
                    date(eventDetails.getInbound().getDate())
                    .flightNumber(eventDetails.getInbound().getFlightNumber())
                    .sourceAirport(eventDetails.getInbound().getAirportCode())
                    .airline(eventDetails.getInbound().getAirlineCode())
                    .build()));
        }
        if(!eventDetails.getOnwards().isEmpty()){
           List<Flight> onwards = new ArrayList<>();
            Flight flight = null;
           for(FlightLeg onwardFlight : eventDetails.getOnwards()){
               flight = Flight.builder().
                       date(onwardFlight.getDate())
                       .flightNumber(onwardFlight.getFlightNumber())
                       .destinationAirport(onwardFlight.getAirportCode())
                       .airline(onwardFlight.getAirlineCode())
                       .build();
               onwards.add(flight);
           }
           flightInformation.setOnward(onwards);
        }
        event.setFlightInformation(flightInformation);
    }

    static void updatePassengerDetails(BagHistoryResponse bagHistoryResponse, List<BagTagEvents> bagTagEvents) {
        BagTagEvents bagTagEvent = findLatestBSMEvent(bagTagEvents);
        if(bagTagEvent != null){
            StringBuilder passengerName = new StringBuilder();
            passengerName.append(bagTagEvent.getEventDetails().getPassenger().getFirstName())
                            .append(" ").append(bagTagEvent.getEventDetails().getPassenger().getMiddleName())
                            .append(" ").append(bagTagEvent.getEventDetails().getPassenger().getLastName());
            bagHistoryResponse.setPassengerName(passengerName.toString());
            bagHistoryResponse.setBagTagNum(bagTagEvent.getEventDetails().getBagTagNumber());
            bagHistoryResponse.setWeightDetails(bagTagEvent.getEventDetails().getWeight());
        }

    }

    static BagTagEvents findLatestBSMEvent(List<BagTagEvents> bagTagEvents) {

        for (BagTagEvents bagTagEvent : bagTagEvents){
            if(bagTagEvent.getEventDetails().getEventCode().equalsIgnoreCase(Constats.BAG_CHECKED_IN))
                return  bagTagEvent;
        }

        return null;
    }

}
