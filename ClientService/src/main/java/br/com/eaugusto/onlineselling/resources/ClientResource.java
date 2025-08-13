package br.com.eaugusto.onlineselling.resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eaugusto.onlineselling.dto.ClientDTO;
import br.com.eaugusto.onlineselling.usecase.RegisterClient;
import br.com.eaugusto.onlineselling.usecase.SearchClient;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

/**
 * REST controller exposing endpoints for managing clients.
 * <p>
 * Provides CRUD operations, search capabilities by ID or CPF, and an endpoint
 * to verify if a client is registered. Uses {@link SearchClient} for read
 * operations and {@link RegisterClient} for write operations.
 * </p>
 *
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 10, 2025
 */
@RestController
@RequestMapping(value = "/client")
public class ClientResource {

	private final SearchClient searchClient;
	private final RegisterClient registerClient;

	public ClientResource(SearchClient searchClient, RegisterClient registerClient) {
		this.searchClient = searchClient;
		this.registerClient = registerClient;
	}

	@GetMapping
	@Operation(summary = "Searches all Clients")
	public ResponseEntity<Page<ClientDTO>> searchAllClients(Pageable pageable) {
		return ResponseEntity.ok(searchClient.searchAllClients(pageable));
	}

	@GetMapping(value = "/{id}")
	@Operation(summary = "Searches a Client by its Id")
	public ResponseEntity<ClientDTO> searchById(@PathVariable String id) {
		return ResponseEntity.ok(searchClient.searchById(id));
	}

	@GetMapping(value = "isRegistered/{id}")
	@Operation(summary = "Checks if a Client exists by searching for its Id")
	public ResponseEntity<Boolean> isRegistered(@PathVariable String id) {
		return ResponseEntity.ok(searchClient.isRegistered(id));
	}

	@PostMapping
	@Operation(summary = "Registers a Client")
	public ResponseEntity<ClientDTO> register(@RequestBody @Valid ClientDTO clientDto) {
		return ResponseEntity.ok(registerClient.register(clientDto));
	}

	@GetMapping(value = "/cpf/{cpf}")
	@Operation(summary = "Searches a Client by CPF")
	public ResponseEntity<ClientDTO> searchByCpf(@PathVariable String cpf) {
		return ResponseEntity.ok(searchClient.searchByCpf(cpf));
	}

	@PutMapping
	@Operation(summary = "Updates a Client")
	public ResponseEntity<ClientDTO> update(@RequestBody @Valid ClientDTO clientDto) {
		return ResponseEntity.ok(registerClient.update(clientDto));
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Removes a Client by its Id")
	public ResponseEntity<String> remove(@PathVariable String id) {
		registerClient.remove(id);
		return ResponseEntity.ok("Removed Successfully");
	}
}
