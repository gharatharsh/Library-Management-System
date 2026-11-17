package com.lms;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Library Management System" , version = "1.0" , description = "This Application contains REST APi's Related to Library Management System")
							, servers = {@Server(url = "http://localhost:8080",description = "localhost")})
public class LibraryManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
		
	}
	
	@Bean
	ModelMapper mapper() {
	    return new ModelMapper();
	}
	
	
	// swagger for doc 
	// http://localhost:8080/swagger-ui/index.html

}
