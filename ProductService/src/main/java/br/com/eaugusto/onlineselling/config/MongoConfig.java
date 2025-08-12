package br.com.eaugusto.onlineselling.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 12, 2025
 */
@Configuration
@EnableMongoRepositories(basePackages = "br.com.eaugusto.onlineselling.repository")
public class MongoConfig {

}
