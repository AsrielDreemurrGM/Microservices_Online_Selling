package br.com.eaugusto.onlineselling.usecase;

import org.springframework.stereotype.Service;

import br.com.eaugusto.onlineselling.domain.Client;
import br.com.eaugusto.onlineselling.dto.ClientDTO;
import br.com.eaugusto.onlineselling.repository.IClientRepository;
import jakarta.validation.Valid;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 10, 2025
 */
@Service
public class RegisterClient {

	private final IClientRepository clientRepository;

	public RegisterClient(IClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	public ClientDTO register(@Valid ClientDTO clientDto) {
		Client client = toEntity(clientDto);
		Client saved = clientRepository.insert(client);
		return toDto(saved);
	}

	public ClientDTO update(@Valid ClientDTO clientDto) {
		Client client = toEntity(clientDto);
		Client updated = clientRepository.save(client);
		return toDto(updated);
	}

	public void remove(String id) {
		clientRepository.deleteById(id);
	}

	private Client toEntity(ClientDTO dto) {
		return Client.builder().id(dto.getId()).name(dto.getName()).cpf(dto.getCpf())
				.telephoneNumber(dto.getTelephoneNumber()).email(dto.getEmail()).address(dto.getAddress())
				.addressNumber(dto.getAddressNumber()).city(dto.getCity()).state(dto.getState()).build();
	}

	private ClientDTO toDto(Client client) {
		return ClientDTO.builder().id(client.getId()).name(client.getName()).cpf(client.getCpf())
				.telephoneNumber(client.getTelephoneNumber()).email(client.getEmail()).address(client.getAddress())
				.addressNumber(client.getAddressNumber()).city(client.getCity()).state(client.getState()).build();
	}
}
