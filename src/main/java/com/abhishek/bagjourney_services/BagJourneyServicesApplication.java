package com.abhishek.bagjourney_services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
//@EnableMongoRepositories(basePackages = "com.abhishek.bagjourney_services.repository")
public class BagJourneyServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BagJourneyServicesApplication.class, args);
	}

}
