package com.ates.dinnerClub;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class DinnerClubApplication {

	public static void main(String[] args) {
		SpringApplication.run(DinnerClubApplication.class, args);
	}

}
