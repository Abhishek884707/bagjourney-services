package com.abhishek.bagjourney_services.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Bag Journey Services",
        version = "1.0",
        description = """
                This API documentation outlines the backend service designed and implemented within the Bag Journey architecture to capture, process, and expose damage and pilferage event data. The service aims to enhance monitoring, accelerate resolution, and support compliance with operational standards.
                  
                  - CheckedIn : C
                  - Damage Bag Reported: DB
                  - Damage Bag Updated: DBU
                  - Damage Bag Delivery Underway: DBDU
                  - Damage Bag Collected: DBC
                  - Damage Bag Delivery Scheduled: DBDS
                  - Damage Bag Delivery Confirmed: DBDC
                  - Damage Bag Received: DBR
                  - Confirm Damage Bag Claim: CDBC
                  - Damage Bag Report Closed: DBRC
                  
                  The mention event code can be used record different events in this event repository.
                """,
        contact = @Contact(name = "Abhishek Kumar Mouriya", email = "2021MT70042@wilp.bits-pilani.ac.in"),
        license = @License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE- 2.0.html")
    )
)
public class OpenApiConfig {
}