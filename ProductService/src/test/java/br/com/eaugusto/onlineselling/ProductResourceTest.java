package br.com.eaugusto.onlineselling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

import br.com.eaugusto.onlineselling.dto.ProductDTO;
import br.com.eaugusto.onlineselling.enums.Status;
import br.com.eaugusto.onlineselling.resources.ProductResource;
import br.com.eaugusto.onlineselling.usecase.RegisterProduct;
import br.com.eaugusto.onlineselling.usecase.SearchProduct;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 12, 2025
 */
class ProductResourceTest {

	@InjectMocks
	private ProductResource productResource;

	@Mock
	private SearchProduct searchProduct;

	@Mock
	private RegisterProduct registerProduct;

	private ProductDTO createProduct() {
		String randomCode = "P" + ThreadLocalRandom.current().nextInt(1000, 9999);
		String randomId = String.valueOf(ThreadLocalRandom.current().nextInt(1, 1000));
		String randomName = "Product " + randomId;

		return ProductDTO.builder().id(randomId).code(randomCode).name(randomName)
				.description("Description for " + randomName).price(BigDecimal
						.valueOf(ThreadLocalRandom.current().nextDouble(10, 1000)).setScale(2, RoundingMode.HALF_UP))
				.status(Status.ACTIVE).build();
	}

	private void assertProductEquals(ProductDTO expected, ProductDTO actual) {
		assertNotNull(actual);
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getCode(), actual.getCode());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getDescription(), actual.getDescription());
		assertEquals(expected.getPrice(), actual.getPrice());
		assertEquals(expected.getStatus(), actual.getStatus());
	}

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void searchById() {
		ProductDTO productDto = createProduct();
		String id = productDto.getId();

		when(searchProduct.searchById(id)).thenReturn(productDto);

		ResponseEntity<ProductDTO> response = productResource.searchById(id);

		assertProductEquals(productDto, response.getBody());
	}

	@Test
	void searchAllProducts() {
		ProductDTO product1 = createProduct();
		ProductDTO product2 = createProduct();

		List<ProductDTO> products = List.of(product1, product2);
		Page<ProductDTO> page = new PageImpl<>(products);

		when(searchProduct.searchAllProducts(any(PageRequest.class))).thenReturn(page);

		ResponseEntity<Page<ProductDTO>> response = productResource.searchAllProducts(PageRequest.of(0, 10));
		Page<ProductDTO> body = response.getBody();

		assertNotNull(body);
		assertEquals(2, body.getContent().size());

		assertProductEquals(product1, body.getContent().get(0));
		assertProductEquals(product2, body.getContent().get(1));
	}

	@Test
	void searchByCode() {
		ProductDTO productDto = createProduct();
		String code = productDto.getCode();

		when(searchProduct.searchByCode(code)).thenReturn(productDto);

		ResponseEntity<ProductDTO> response = productResource.searchByCode(code);

		assertProductEquals(productDto, response.getBody());
	}

	@Test
	void isRegistered() {
		when(searchProduct.isRegistered("1")).thenReturn(true);

		ResponseEntity<Boolean> response = productResource.isRegistered("1");
		Boolean isRegistered = response.getBody();

		assertNotNull(isRegistered);
		assertTrue(isRegistered);
	}

	@Test
	void registerProduct() {
		ProductDTO inputDto = createProduct();
		ProductDTO savedDto = createProduct();

		when(registerProduct.register(inputDto)).thenReturn(savedDto);

		ResponseEntity<ProductDTO> response = productResource.register(inputDto);
		ProductDTO body = response.getBody();

		assertProductEquals(savedDto, body);
	}

	@Test
	void updateProduct() {
		ProductDTO inputDto = createProduct();
		ProductDTO updatedDto = createProduct();

		when(registerProduct.update(inputDto)).thenReturn(updatedDto);

		ResponseEntity<ProductDTO> response = productResource.update(inputDto);
		ProductDTO body = response.getBody();

		assertProductEquals(updatedDto, body);
	}

	@Test
	void removeProduct() {
		doNothing().when(registerProduct).remove("1");

		ResponseEntity<String> response = productResource.remove("1");
		String body = response.getBody();

		assertNotNull(body);
		assertEquals("Removed Successfully", body);
	}
}
