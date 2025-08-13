package br.com.eaugusto.onlineselling.usecase;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.eaugusto.onlineselling.domain.Client;
import br.com.eaugusto.onlineselling.dto.ClientDTO;
import br.com.eaugusto.onlineselling.exception.EntityNotFoundException;
import br.com.eaugusto.onlineselling.repository.IClientRepository;

/**
 * Use case service for searching and retrieving client information.
 * <p>
 * Provides paginated search for all clients, lookup by ID, CPF, and existence
 * check. Converts {@link Client} entities to {@link ClientDTO} for API
 * responses.
 * </p>
 *
 * <p>
 * Throws {@link EntityNotFoundException} when requested resources are not found
 * in the database.
 * </p>
 *
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 10, 2025
 */
@Service
public class SearchClient {

	private final IClientRepository clientRepository;

	public SearchClient(IClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	public Page<ClientDTO> searchAllClients(Pageable pageable) {
		return clientRepository.findAll(pageable).map(this::toDto);
	}

	public ClientDTO searchById(String id) {
		Client client = clientRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Client.class, "id", id));
		return toDto(client);
	}

	public Boolean isRegistered(String id) {
		Optional<Client> client = clientRepository.findById(id);
		return client.isPresent();
	}

	public ClientDTO searchByCpf(String cpf) {
		Client client = clientRepository.searchByCpf(cpf)
				.orElseThrow(() -> new EntityNotFoundException(Client.class, "cpf", String.valueOf(cpf)));
		return toDto(client);
	}

	private ClientDTO toDto(Client client) {
		return ClientDTO.builder().id(client.getId()).name(client.getName()).cpf(client.getCpf())
				.telephoneNumber(client.getTelephoneNumber()).email(client.getEmail()).address(client.getAddress())
				.addressNumber(client.getAddressNumber()).city(client.getCity()).state(client.getState()).build();
	}
}
