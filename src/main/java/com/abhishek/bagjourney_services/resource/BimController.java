package com.abhishek.bagjourney_services.resource;

import com.abhishek.bagjourney_services.dto.BagEvent;
import com.abhishek.bagjourney_services.entity.BagTagEvents;
import com.abhishek.bagjourney_services.model.EventDetails;
import com.abhishek.bagjourney_services.services.BagEventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BimController {

    @Autowired
    BagEventProcessor processor;

    @GetMapping("/bsm")
    public String getBimMessage(){
        return "Hello World";
    }

    @PostMapping("/bsm")
    public BagTagEvents processBimMessage(@RequestBody BagEvent bagEvent){
        return processor.process(bagEvent);
    }
}
