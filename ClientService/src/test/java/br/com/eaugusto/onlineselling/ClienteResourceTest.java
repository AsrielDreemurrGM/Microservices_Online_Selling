package br.com.eaugusto.onlineselling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import br.com.eaugusto.onlineselling.dto.ClientDTO;
import br.com.eaugusto.onlineselling.resources.ClientResource;
import br.com.eaugusto.onlineselling.usecase.RegisterClient;
import br.com.eaugusto.onlineselling.usecase.SearchClient;

/**
 * Unit tests for {@link ClientResource}.
 * <p>
 * Uses Mockito to mock dependencies ({@link SearchClient} and
 * {@link RegisterClient}) and verify that the controller's endpoints behave as
 * expected.
 * </p>
 *
 * <p>
 * Test coverage includes:
 * </p>
 * <ul>
 * <li>Searching clients by ID, CPF, and pagination.</li>
 * <li>Checking if a client is registered.</li>
 * <li>Registering, updating, and removing clients.</li>
 * </ul>
 *
 * <p>
 * Relies on generated random test data via {@link ThreadLocalRandom} to
 * simulate different client entries.
 * </p>
 *
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 10, 2025
 */
class ClienteResourceTest {

	@InjectMocks
	private ClientResource clienteResource;

	@Mock
	private SearchClient searchClient;

	@Mock
	private RegisterClient registerClient;

	private ClientDTO createClient() {
		String randomCpf = String.valueOf(ThreadLocalRandom.current().nextLong(10000000000L, 99999999999L));
		String randomId = String.valueOf(ThreadLocalRandom.current().nextInt(1, 1000));
		String randomName = "Client " + randomId;

		return ClientDTO.builder().id(randomId).name(randomName).cpf(randomCpf).telephoneNumber("123456-5678")
				.email("client" + randomId + "@example.com").address("Java Street").addressNumber(100).city("JPA City")
				.state("Spring State").build();
	}

	private void assertClientEquals(ClientDTO expected, ClientDTO actual) {
		assertNotNull(actual);
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getCpf(), actual.getCpf());
		assertEquals(expected.getTelephoneNumber(), actual.getTelephoneNumber());
		assertEquals(expected.getEmail(), actual.getEmail());
		assertEquals(expected.getAddress(), actual.getAddress());
		assertEquals(expected.getAddressNumber(), actual.getAddressNumber());
		assertEquals(expected.getCity(), actual.getCity());
		assertEquals(expected.getState(), actual.getState());
	}

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void searchById() {
		ClientDTO clientDto = createClient();
		String id = clientDto.getId();

		when(searchClient.searchById(id)).thenReturn(clientDto);

		ResponseEntity<ClientDTO> response = clienteResource.searchById(id);

		assertClientEquals(clientDto, response.getBody());
	}

	@Test
	void searchAllClients() {
		ClientDTO client1 = createClient();
		ClientDTO client2 = createClient();

		List<ClientDTO> clients = List.of(client1, client2);
		Page<ClientDTO> page = new PageImpl<>(clients);

		when(searchClient.searchAllClients(any(PageRequest.class))).thenReturn(page);

		ResponseEntity<Page<ClientDTO>> response = clienteResource.searchAllClients(PageRequest.of(0, 10));
		Page<ClientDTO> body = response.getBody();

		assertNotNull(body);
		assertEquals(2, body.getContent().size());

		assertClientEquals(client1, body.getContent().get(0));
		assertClientEquals(client2, body.getContent().get(1));
	}

	@Test
	void searchByCpf() {
		ClientDTO clientDto = createClient();
		String cpf = clientDto.getCpf();

		when(searchClient.searchByCpf(cpf)).thenReturn(clientDto);

		ResponseEntity<ClientDTO> response = clienteResource.searchByCpf(cpf);

		assertClientEquals(clientDto, response.getBody());
	}

	@Test
	void isRegistered() {
		when(searchClient.isRegistered("1")).thenReturn(true);

		ResponseEntity<Boolean> response = clienteResource.isRegistered("1");
		Boolean isRegistered = response.getBody();

		assertNotNull(isRegistered);
		assertTrue(isRegistered);
	}

	@Test
	void registerClient() {
		ClientDTO inputDto = createClient();
		ClientDTO savedDto = createClient();

		when(registerClient.register(inputDto)).thenReturn(savedDto);

		ResponseEntity<ClientDTO> response = clienteResource.register(inputDto);
		ClientDTO body = response.getBody();

		assertClientEquals(savedDto, body);
	}

	@Test
	void updateClient() {
		ClientDTO inputDto = createClient();
		ClientDTO updatedDto = createClient();

		when(registerClient.update(inputDto)).thenReturn(updatedDto);

		ResponseEntity<ClientDTO> response = clienteResource.update(inputDto);
		ClientDTO body = response.getBody();

		assertClientEquals(updatedDto, body);
	}

	@Test
	void removeClient() {
		doNothing().when(registerClient).remove("1");

		ResponseEntity<String> response = clienteResource.remove("1");
		String body = response.getBody();

		assertNotNull(body);
		assertEquals("Removed Successfully", body);
	}
}
