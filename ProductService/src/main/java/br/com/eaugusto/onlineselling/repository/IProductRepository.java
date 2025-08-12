package br.com.eaugusto.onlineselling.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.eaugusto.onlineselling.domain.Product;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 10, 2025
 */
@Repository
public interface IProductRepository extends MongoRepository<Product, String> {

	Optional<Product> searchByCode(String code);
}
