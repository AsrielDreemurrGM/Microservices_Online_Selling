package br.com.eaugusto.onlineselling.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * MongoDB configuration class.
 * <p>
 * Enables the scanning of MongoDB repositories in the specified base package.
 * </p>
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 12, 2025
 */
@Configuration
@EnableMongoRepositories(basePackages = "br.com.eaugusto.onlineselling.repository")
public class MongoConfig {

}
