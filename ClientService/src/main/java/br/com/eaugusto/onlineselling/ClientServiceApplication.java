package br.com.eaugusto.onlineselling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Client Service microservice.
 * <p>
 * This service handles client-related operations, such as registration, search,
 * update, and removal, using MongoDB as the database.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 09, 2025
 */
@SpringBootApplication
public class ClientServiceApplication {

	/**
	 * Main method that starts the Spring Boot application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(ClientServiceApplication.class, args);
	}
}
