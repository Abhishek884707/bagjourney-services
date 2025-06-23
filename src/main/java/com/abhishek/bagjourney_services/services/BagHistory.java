package com.abhishek.bagjourney_services.services;

import com.abhishek.bagjourney_services.entity.BagTagEvents;

import java.util.List;

public interface BagHistory {

    public List<BagTagEvents> getListOfEvents(String bagTagNumber, String date, String lastName, String pnr);

}
