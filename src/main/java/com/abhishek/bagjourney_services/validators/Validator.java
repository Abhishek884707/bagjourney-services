package com.abhishek.bagjourney_services.validators;

import com.abhishek.bagjourney_services.dto.BagEventRequest;
import com.abhishek.bagjourney_services.exception.BagJourneyValidationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Validator {

    public boolean validateGetBagHistoryParameters(String BagTagNumber, String date, String lastName, String pnr) {
        boolean isValid = false;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-MM");
        if(BagTagNumber.isBlank() || date.isBlank()){
            throw new BagJourneyValidationException("BagTagNumber and Date", "Bag Tag Number and Date can't be blank.");
        }else if(BagTagNumber.length() < 10){
            throw  new BagJourneyValidationException("BagTagNumber", "Bag Tag Number can't be less then 10.");
        }else if(!date.isBlank()){
            try {
                format.parse(date);
            } catch (ParseException e) {
                throw new BagJourneyValidationException("Date", "Date is given is wrong format accepts only(yyyy-dd-MM).");
            }
        }
        return isValid;
    }

    public boolean validateRequest(BagEventRequest bagEventRequest) {
        boolean isValid = true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-MM");
        if(bagEventRequest.getEventCode() == null){
            isValid = false;
            throw new BagJourneyValidationException("Event Code", "Bag Event Code can't be blank.");
        }
        return isValid;
    }

}
