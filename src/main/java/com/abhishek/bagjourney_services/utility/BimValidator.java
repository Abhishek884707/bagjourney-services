package com.abhishek.bagjourney_services.utility;

import com.abhishek.bagjourney_services.dto.BagEventRequest;
import com.abhishek.bagjourney_services.model.ErrorCodes;
import org.springframework.stereotype.Component;

@Component
public class BimValidator {

    public Boolean validate(BagEventRequest request) {
        if(request.getBagTag() == null){
            throw new RuntimeException("BJ0001");
        }

        return true;
    }

}
