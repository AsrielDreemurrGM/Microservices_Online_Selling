package br.com.eaugusto.onlineselling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Main entry point for the Sales Service application.
 * <p>
 * This class bootstraps the Spring Boot application, enabling component
 * scanning, auto-configuration, Feign clients, and refresh scope for dynamic
 * configuration updates.
 * </p>
 *
 * <p>
 * Excludes automatic DataSource configuration as this service does not manage a
 * SQL database.
 * </p>
 *
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 09, 2025
 */
@SpringBootApplication
@RefreshScope
@EnableFeignClients
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class SalesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesServiceApplication.class, args);
	}
}
