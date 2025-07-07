package com.abhishek.bagjourney_services.repository;

import com.abhishek.bagjourney_services.entity.BagTagEvents;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface BagTagEventsRepository extends MongoRepository<BagTagEvents, String> {

    List<BagTagEvents> findByMasterBagIdOrderByEventDateAsc(String bagTagId);

}
