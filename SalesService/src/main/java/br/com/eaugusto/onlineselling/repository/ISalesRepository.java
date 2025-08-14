package br.com.eaugusto.onlineselling.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.eaugusto.onlineselling.domain.Sales;

/**
 * Repository interface for managing {@link Sales} entities in MongoDB.
 * <p>
 * Extends {@link MongoRepository} to provide basic CRUD operations and adds a
 * custom query method for searching a sale by code.
 * </p>
 *
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 14, 2025
 */
@Repository
public interface ISalesRepository extends MongoRepository<Sales, String> {

	Optional<Sales> searchByCode(String code);
}
