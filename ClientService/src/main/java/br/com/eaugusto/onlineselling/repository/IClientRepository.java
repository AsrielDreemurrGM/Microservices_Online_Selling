package br.com.eaugusto.onlineselling.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.eaugusto.onlineselling.domain.Client;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 10, 2025
 */
@Repository
public interface IClientRepository extends MongoRepository<Client, String> {

	Optional<Client> searchByCpf(String cpf);
}
