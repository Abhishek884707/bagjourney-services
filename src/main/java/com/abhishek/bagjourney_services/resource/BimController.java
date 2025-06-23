package com.abhishek.bagjourney_services.resource;

import com.abhishek.bagjourney_services.dto.BagEventRequest;
import com.abhishek.bagjourney_services.dto.BagEventResponse;
import com.abhishek.bagjourney_services.dto.BagHistoryResponse;
import com.abhishek.bagjourney_services.dto.ErrorResponse;
import com.abhishek.bagjourney_services.entity.BagTagEvents;
import com.abhishek.bagjourney_services.services.BagEventProcessor;
import com.abhishek.bagjourney_services.services.BagHistory;
import com.abhishek.bagjourney_services.utility.BagHistoryTransformer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "BIM", description = "Rest endpoint to process the message and fetch the Bag History.")
@RestController
@RequestMapping("/v1")
public class BimController {

    @Autowired
    BagEventProcessor processor;

    @Autowired
    BagHistory bagHistory;


    @Operation(
            summary = "Fetch the History of Bag.",
            description = "Fetches all the events happens between the source and destination stations.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation."),
            @ApiResponse(responseCode = "400",
                    description = "Bag Request.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    @GetMapping("/bim")
    public BagHistoryResponse getBimMessage(@RequestParam String bagTagNumber, @RequestParam String date,
                                @RequestParam String lastname, @RequestParam String pnr){
        List<BagTagEvents> bagTagEventsList = bagHistory.getListOfEvents(bagTagNumber, date, lastname, pnr);
        BagHistoryResponse bagHistoryResponse = BagHistoryTransformer.transform(bagTagEventsList);
        return bagHistoryResponse;
    }

    @Operation(
            summary = "Process Bag Tag Event.",
            description = "Process the event request and store the them in to database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation."),
            @ApiResponse(responseCode = "400",
                    description = "Bag Request.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    @PostMapping("/bim")
    public BagEventResponse processBimMessage(@RequestBody BagEventRequest bagEvent){
        Boolean status = processor.process(bagEvent);
        BagEventResponse response;
        if(status){
            response = BagEventResponse.builder()
                    .status(status)
                    .message("Message Processed Successfully")
                    .build();
            return response;
        }else{
             response = BagEventResponse.builder()
                    .status(false)
                    .message("Exception Occurred while processing the request.")
                    .build();
            return response;
        }
    }
}
