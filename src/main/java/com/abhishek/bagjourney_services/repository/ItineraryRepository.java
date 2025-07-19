package com.abhishek.bagjourney_services.repository;

import com.abhishek.bagjourney_services.entity.BagItinerary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ItineraryRepository extends MongoRepository<BagItinerary, String> {

//    @Query("{bagTagId: ?0, departedStation: ?1}")
    BagItinerary findByBagTagIdAndDepartedStation(String bagTagId, String departedStation);

//    @Query("{bagTagId:'?0', date: '?1'}")
    List<BagItinerary> findByBagTagIdAndDate(String bagTagId, String date);

//    @Query("{bagTagId:'?0', date: '?1', lastName: '?2'}")
    List<BagItinerary> findByBagTagIdAndDateAndLastNameIgnoreCase(String bagTagId, String date, String lastName);

//    @Query("{bagTagId:'?0', date: '?1', lastName: '?2', pnrNumber: '?3'}")
    List<BagItinerary> findByBagTagIdAndDateAndPnrNumberIgnoreCase(String bagTagId, String date, String pnrNumber);
}
