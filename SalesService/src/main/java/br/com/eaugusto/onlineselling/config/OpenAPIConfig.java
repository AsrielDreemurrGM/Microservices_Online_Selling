package br.com.eaugusto.onlineselling.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

/**
 * OpenAPI (Swagger) configuration for the Sales Service.
 * <p>
 * Defines metadata for API documentation, including version, description, and
 * contact details.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 14, 2025
 */
@Configuration
public class OpenAPIConfig {

	/**
	 * Creates a custom {@link OpenAPI} instance with application metadata.
	 * 
	 * @param appVersion The application version, injected from properties.
	 * @return Configured {@link OpenAPI} instance.
	 */
	@Bean
	OpenAPI customOpenAPI(@Value("${application-version}") String appVersion) {
		return new OpenAPI().info(new Info().title("Sales Service").version(appVersion)
				.description("Service for managing Sales").termsOfService("http://swagger.io/terms/")
				.contact(new Contact().name("Eduardo Augusto").email("eduardo.motta.980315@gmail.com")));
	}
}
