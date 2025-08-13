package br.com.eaugusto.onlineselling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Main entry point for the Config Server application.
 * <p>
 * This service provides centralized external configuration management for all
 * microservices in the system. It serves configuration properties from local
 * YML files (native profile) for the following services:
 * <ul>
 * <li><b>client-service.yml</b> - Contains MongoDB connection details and
 * version information for ClientService.</li>
 * <li><b>product-service.yml</b> - Contains MongoDB connection details and
 * version information for ProductService.</li>
 * <li><b>sales-service.yml</b> - Contains MongoDB connection details, version
 * information, and service endpoints for SalesService.</li>
 * </ul>
 * <p>
 * This configuration server is enabled via {@link EnableConfigServer} and uses
 * Spring Cloud Config's native file system backend.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 13, 2025
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

	/**
	 * Application entry point. Initializes and starts the Spring Boot Config
	 * Server.
	 */
	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}
