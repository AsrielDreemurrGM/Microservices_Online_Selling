package br.com.eaugusto.onlineselling.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 11, 2025
 */
@Configuration
public class OpenAPIConfig {

	@Bean
	OpenAPI customOpenAPI(@Value("${application-version}") String appVersion) {
		return new OpenAPI().info(new Info().title("Client Service").version(appVersion)
				.description("Service for managing Clients").termsOfService("http://swagger.io/terms/")
				.contact(new Contact().name("Eduardo Augusto").email("eduardo.motta.980315@gmail.com")));
	}

}
