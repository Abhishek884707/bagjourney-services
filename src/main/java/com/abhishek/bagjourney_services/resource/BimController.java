package com.abhishek.bagjourney_services.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BimController {

    @GetMapping("/bsm")
    public String BSM(){
        return "Hello World";
    }

}
