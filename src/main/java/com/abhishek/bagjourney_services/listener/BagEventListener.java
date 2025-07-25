package com.abhishek.bagjourney_services.listener;

import com.abhishek.bagjourney_services.controllers.BimController;
import com.abhishek.bagjourney_services.dto.BagEventRequest;
import com.abhishek.bagjourney_services.model.Constats;
import com.abhishek.bagjourney_services.validators.Validator;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
public class BagEventListener {

    private final ObjectMapper objectMapper;

    @Autowired
    BimController bimController;

    Validator validator;
    public BagEventListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.validator = new Validator();
    }

    @JmsListener(destination = Constats.BAG_EVENT_QUEUE)
    public void listen(Message message) {
        try {
            String json = message.getBody(String.class);
            System.out.println("Bag event received: " + json);
            BagEventRequest bagEventRequest = objectMapper.readValue(json, BagEventRequest.class);

            if(validator.validateRequest(bagEventRequest)){
                bimController.processBimMessage(bagEventRequest);
            }

        } catch (Exception e) {
            System.err.println("Failed to process message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
