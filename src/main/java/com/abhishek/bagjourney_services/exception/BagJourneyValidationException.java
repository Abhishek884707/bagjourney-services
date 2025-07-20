package com.abhishek.bagjourney_services.exception;

import com.abhishek.bagjourney_services.model.ErrorCodes;
import lombok.Getter;

    public class BagJourneyValidationException extends RuntimeException{

    @Getter
    String field;
    String message;

    public BagJourneyValidationException(String field, String message){
        super(message);
        this.field= field;
        this.message = message;
    }

}
